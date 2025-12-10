import pytest
import pandas as pd
from datetime import datetime
from unittest.mock import patch, Mock
from requests.exceptions import RequestException
import json

from app import (
    fetch_stock_price,
    process_time_series_data,
    filter_data,
    analyze_data
)

# Sample raw data for testing
DAILY_TIME_SERIES_DATA = {
    "Meta Data": {
        "1. Information": "Daily Prices (open, high, low, close) and Volumes",
        "2. Symbol": "IBM",
        "3. Last Refreshed": "2023-10-20",
        "4. Output Size": "Compact",
        "5. Time Zone": "US/Eastern"
    },
    "Time Series (Daily)": {
        "2023-10-20": {
            "1. open": "140.0000",
            "2. high": "142.0000",
            "3. low": "139.5000",
            "4. close": "141.5000",
            "5. volume": "3500000.0"
        },
        "2023-10-19": {
            "1. open": "138.0000",
            "2. high": "140.5000",
            "3. low": "137.5000",
            "4. close": "139.0000",
            "5. volume": "3000000.0"
        },
        "2023-11-01": {
            "1. open": "128.0000",
            "2. high": "130.5000",
            "3. low": "137.5000",
            "4. close": "129.0000",
            "5. volume": "2000000.0"
        },
        "2023-11-21": {
            "1. open": "148.0000",
            "2. high": "150.5000",
            "3. low": "147.5000",
            "4. close": "149.0000",
            "5. volume": "4000000.0"
        },

    }
}

class MockResponse:
    def __init__(self, json_data, status_code=200):
        self._json_data = json_data
        self.status_code = status_code
        self.raise_for_status_called = False
        self.text = json.dumps(json_data)

    def json(self):
        return self._json_data

    def raise_for_status(self):
        self.raise_for_status_called = True
        if self.status_code != 200:
            raise RequestException(f"HTTP {self.status_code} Error")

@patch('app.requests.get')
def test_fetch_stock_price_success(mock_get):
    mock_get.return_value = MockResponse(
        json_data= {
            "Global Quote": {'01. symbol': 'TEST', '05. price': '123.45'},
        },
        status_code=200
    ) 

    result = fetch_stock_price('TEST')

    assert result is not None, "Result should not be None on successful fetch"
    # assert "Meta Data" in result, "Result should contain 'Meta Data'"
    assert result["Global Quote"]['01. symbol'] == 'TEST', "Symbol should match the requested symbol"
    assert result["Global Quote"]['05. price'] == '123.45', "Price should match the mocked price"

    mock_get.assert_called_once()

@patch('app.requests.get')
def test_fetch_stock_price_api_error(mock_get):
    mock_get.return_value = MockResponse(
        json_data={"Error Message": "Invalid API call."},
        status_code=200
    )

    result = fetch_stock_price('INVALID')

    assert result is None, "Result should be None when API returns an error message"
    mock_get.assert_called_once()

@patch('app.requests.get', side_effect=RequestException("Network error"))
def test_fetch_stock_price_network_error(mock_get):
    result = fetch_stock_price('TEST')

    assert result is None, "Result should be None when a network error occurs"
    mock_get.assert_called_once()

@pytest.fixture(scope="module")
def processed_df():
    df = process_time_series_data(DAILY_TIME_SERIES_DATA)
    return df

@pytest.fixture(scope="module")
def analyzed_df(processed_df):
    df = analyze_data(processed_df)
    return df

def test_process_time_series_data(processed_df):
    df = processed_df
    assert df is not None, "Processed DataFrame should not be None"
    assert isinstance(df, pd.DataFrame), "Processed data should be a DataFrame"
    assert not df.empty, "Processed DataFrame should not be empty"
    assert list(df.columns) == ['open', 'high', 'low', 'close', 'volume'], "Column names should be cleaned"
    assert pd.api.types.is_datetime64_any_dtype(df.index), "Index should be datetime type"
    assert df.index.is_monotonic_increasing, "DataFrame should be sorted by date"

def test_process_time_series_data_data_types(processed_df):
    assert processed_df['open'].dtype == float, "Open column should be float"
    assert processed_df['high'].dtype == float, "High column should be float"
    assert processed_df['low'].dtype == float, "Low column should be float"
    assert processed_df['close'].dtype == float, "Close column should be float"
    assert processed_df['volume'].dtype == float, "Volume column should be int"

def test_process_time_series_data_missing_key():
    incomplete_data = {
        "Meta Data": {},
        "Wrong Key": {}
    }

    result = process_time_series_data(incomplete_data)
    assert result is None, "Function should return None for incomplete data"

def test_process_time_series_data_empty():
    empty_data = {
        "Meta Data": {},
        "Time Series (Daily)": {}
    }

    result = process_time_series_data(empty_data)
    assert result is not None, "Function should return an empty DataFrame for empty time series data"
    assert result.empty, "Returned DataFrame should be empty"

def test_filter_data_correct_range(processed_df):
    df = processed_df
    start_date = "2023-10-19"
    end_date = "2023-10-20"
    filtered_df = filter_data(df, start_date, end_date)
    assert len(filtered_df) == 2, "Filtered DataFrame should have 2 records"
    assert not filtered_df.empty, "Filtered DataFrame should not be empty"
    assert filtered_df.index.min() >= pd.to_datetime(start_date), "Filtered DataFrame should start from start_date"
    assert filtered_df.index.max() <= pd.to_datetime(end_date), "Filtered DataFrame should end at end_date"

def test_filter_data_no_data_in_range(processed_df):
    df = processed_df
    start_date = "2022-01-01"
    end_date = "2022-01-31"
    filtered_df = filter_data(df, start_date, end_date)
    assert filtered_df.empty, "Filtered DataFrame should be empty when no data in range"

def test_analyze_data_returns_columns_exists(analyzed_df):
    df = analyzed_df
    assert 'daily_return' in df.columns, "DataFrame should contain 'daily_return' column"
    assert 'moving_average_20' in df.columns, "DataFrame should contain 'moving_average_20' column"

