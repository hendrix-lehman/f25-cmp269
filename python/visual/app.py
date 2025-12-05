#!/usr/bin/env python3

import requests
import json
from typing import Dict, Any
import matplotlib.pyplot as plt
import sys

import pandas as pd

# Configuration to get data from Alpha Vantage API
API_BASE_URL = "https://www.alphavantage.co/query"
# API_KEY = "demo"  # Replace with your actual API key
API_KEY = "N2JO6YTQZSNAECNO"  # Replace with your actual API key

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

def plot_stock_data(df: pd.DataFrame, symbol: str) -> None:
    if df.empty:
        print("No data available to plot.")
        return

    fig, axes = plt.subplots(nrows=2, ncols=1, figsize=(12, 8))

    fig.suptitle(f'{symbol} Stock Price and Return Analysis', fontsize=16)

    axes[0].plot(df.index, df['close'], label='Closing Price', color='skyblue', linewidth=2)
    axes[0].set_title('Price History', fontsize=14)
    axes[0].set_ylabel('Price ($)', fontsize=12)
    axes[0].grid(True, linestyle='--', alpha=0.7)
    axes[0].legend()
    axes[0].tick_params(axis='x', rotation=45)

    return_colors = ['green' if x >= 0 else 'red' for x in df['daily_return']]
    axes[1].bar(df.index, df['daily_return'], label='Daily Return (%)', color=return_colors, alpha=0.7)
    axes[1].set_title('Daily Returns', fontsize=14)
    # axes[1].set_xlabel('Date', fontsize=12)
    axes[1].set_ylabel('Return (%)', fontsize=12)
    axes[1].axhline(0, color='black', linewidth=0.8, linestyle='-')
    axes[1].grid(True, linestyle='--', alpha=0.7)
    axes[1].legend()
    axes[1].tick_params(axis='x', rotation=45)

    plt.tight_layout(rect=[0, 0.03, 1, 0.95])
    plt.show()

if __name__ == "__main__":
    # read symbol from command line argument if provided
    if len(sys.argv) > 1:
        symbol = sys.argv[1]

        stock_data = fetch_stock_price(symbol=symbol, function='TIME_SERIES_DAILY')

        if stock_data:
            print(json.dumps(stock_data, indent=2))
        else:
            print("Failed to retrieve stock data.")

        stock_df = process_time_series_data(stock_data)

        if stock_df is not None:
            for index, row in stock_df.iterrows():
                print(f"Date: {index.date()}, Open: {row['open']}, High: {row['high']}, Low: {row['low']}, Close: {row['close']}, Volume: {row['volume']}")


        analysed_df = analyze_data(stock_df)
        print("\nAnalyzed Data Sample:")
        print(analysed_df.tail())

        print("\n" + "="*80)
        print('Visualizing Stock Data')
        plot_stock_data(analysed_df, symbol=symbol)
    else:
        print('Usage: python app.py <STOCK_SYMBOL>')

