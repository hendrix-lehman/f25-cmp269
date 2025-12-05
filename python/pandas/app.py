#!/usr/bin/env python3

import requests
import json
from typing import Dict, Any

import pandas as pd

# Configuration to get data from Alpha Vantage API
API_BASE_URL = "https://www.alphavantage.co/query"
# API_KEY = "demo"  # Replace with your actual API key
API_KEY = "demo"  # Replace with your actual API key

def fetch_stock_price(symbol: str, function: str = 'GLOBAL_QUOTE') -> Dict[str, Any] | None:
    # 1. Construct the request URL with parameters
    params = {
        'function': function,
        'symbol': symbol,
        'apikey': API_KEY
    }

    try:
        # 2. Make the GET request to the API
        response = requests.get(API_BASE_URL, params=params, timeout=10)
        response.raise_for_status()  # Raise an error for bad responses

        # 3. Parse the JSON response
        data = response.json()

        # Check for Alpha Vantage specific error messages
        if "Error Message" in data:
            print(f"Error from API: {data['Error Message']}")
            return None

        if "Note" in data:
            print(f"API Notice: {data['Note']}")
            return None

        return data

    except requests.RequestException as e:
        print(f"Request failed: {e}")
        return None
    except json.JSONDecodeError as e:
        print(f"JSON decode error: {e}")
        return None

def process_time_series_data(raw_data: Dict[str, Any]) -> pd.DataFrame | None:

    # 1. Identify the time series key dynamically
    time_series_key = next((key for key in raw_data if "Time Series" in key), None)

    if not time_series_key:
        print("Time Series data not found in the response.")
        return None

    # 2. Convert the nested dictionary to a DataFrame 
    df = pd.DataFrame.from_dict(raw_data[time_series_key], orient='index')

    # 3. Clean up the column names
    # Alpha Vantage columns are prefixed with numbers and periods, e.g., '1. open', '2. high'. We remove these prefixes.
    df.columns = [col.split('. ')[1] for col in df.columns]

    # 4. Convert all columns to numeric types
    df = df.astype(float)

    # 5. Ensure the index is datetime
    df.index = pd.to_datetime(df.index)

    # 6. Sort the data by date (oldest to newest)
    df = df.sort_index()

    return df

def filter_data(df: pd.DataFrame, start_date: str, end_date: str) -> pd.DataFrame:
    print(f"Filtering data from {start_date} to {end_date}")
    # Pandas slicing on a DatatimeIndex is inclusive of both start and end dates
    filtered_df = df.loc[start_date:end_date]
    print(f"Original records: {len(df)}. Filtered records: {len(filtered_df)}")
    return filtered_df

def analyze_data(df: pd.DataFrame) -> pd.DataFrame:

    df['daily_return'] = df['close'].pct_change() * 100

    df['moving_average_20'] = df['close'].rolling(window=20).mean()

    return df

if __name__ == "__main__":
    symbol = "GOOGL"  # Example stock symbol
    stock_data = fetch_stock_price(symbol=symbol, function='TIME_SERIES_DAILY')

    if stock_data:
        print(json.dumps(stock_data, indent=2))
    else:
        print("Failed to retrieve stock data.")

    stock_df = process_time_series_data(stock_data)

    if stock_df is not None:
        # Display the first 5 rows of the DataFrame
        print(stock_df.head())

        for index, row in stock_df.iterrows():
            print(f"Date: {index.date()}, Open: {row['open']}, High: {row['high']}, Low: {row['low']}, Close: {row['close']}, Volume: {row['volume']}")

        print ("---- Filtering Data ----")

        # Example filtering
        filtered_stock_df = filter_data(stock_df, start_date="2025-08-01", end_date="2025-08-31")
        for index, row in filtered_stock_df.iterrows():
            print(f"Filtered Date: {index.date()}, Open: {row['open']}, High: {row['high']}, Low: {row['low']}, Close: {row['close']}, Volume: {row['volume']}")

        print ("---- Analyzing Data ----")
        analyzed_stock_df = analyze_data(stock_df)
        print("\nStatistical Summary of Closing Prices:")
        print(analyzed_stock_df['close'].describe())

        print("\nStatistical Summary of Daily Returns (%):")
        print(analyzed_stock_df['daily_return'].describe())

        print("\n20 days Moving Average:")
        print(analyzed_stock_df['moving_average_20'].dropna().head())

        volatility = analyzed_stock_df['daily_return'].std()
        print(f"\nVolatility (Standard Deviation of Daily Returns): {volatility:.2f}%")

# Pandas Series: Data points and index 
# series_data = pd.Series(
#     data=[301.78, 301.57, 310.46, 307.00],
#     index=pd.to_datetime([
#         "2024-06-20 16:00:00",
#         "2024-06-21 16:00:00",
#         "2024-06-24 16:00:00",
#         "2024-06-25 16:00:00"
#     ])
# )

# Pandas DataFrame: Tabular data structure

#     series_data = pd.Series(
#         data=[301.78, 301.57, 310.46, 307.00],
#         index=pd.to_datetime([
#             "2024-06-20 16:00:00",
#             "2024-06-21 16:00:00",
#             "2024-06-24 16:00:00",
#             "2024-06-25 16:00:00"
#         ])
#     )
#     print(series_data)

#     day1 = pd.Series(
#         data=[301.78, 301.57, 310.46, 307.00],
#         index=['open', 'high', 'low', 'close']
#     )
#     day2 = pd.Series(
#         data=[308.00, 312.00, 300.00, 305.00],
#         index=['open', 'high', 'low', 'close']
#     )
#     data = {
#         '2024-06-20': day1,
#         '2024-06-21': day2
#     }

#     data_frame = pd.DataFrame(data)
#     print(data_frame)

#     # access specific data points
#     print("Day 1 Close Price:", data_frame['2024-06-20']['close'])


