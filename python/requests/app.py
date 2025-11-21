#!/usr/bin/env python3

import requests
import pandas as pd
import json
from typing import Dict, Any

API_URL = "https://www.alphavantage.co/query"

def fetch_company_overview(symbol: str, api_key: str) -> dict: 
    url = f"{API_URL}?function=OVERVIEW&symbol={symbol}&apikey={api_key}"
    response = requests.get(url)
    if(not response.ok):
        print("Error fetching data:", response.status_code, response.text)
        response.raise_for_status()  # Raise an error for bad responses
    return response.json()

def fetch_balance_sheet(symbol: str, api_key: str) -> dict:
    url = f"{API_URL}?function=BALANCE_SHEET&symbol={symbol}&apikey={api_key}"
    response = requests.get(url)
    if(not response.ok):
        print("Error fetching data:", response.status_code, response.text)
        response.raise_for_status()  # Raise an error for bad responses
    return response.json()

def fetch_income_statement(symbol: str, api_key: str) -> dict:
    url = f"{API_URL}?function=INCOME_STATEMENT&symbol={symbol}&apikey={api_key}"
    response = requests.get(url)
    if(not response.ok):
        print("Error fetching data:", response.status_code, response.text)
        response.raise_for_status()  # Raise an error for bad responses
    return response.json()

def fetch_cash_flow(symbol: str, api_key: str) -> dict:
    url = f"{API_URL}?function=CASH_FLOW&symbol={symbol}&apikey={api_key}"
    response = requests.get(url)
    if(not response.ok):
        print("Error fetching data:", response.status_code, response.text)
        response.raise_for_status()  # Raise an error for bad responses
    return response.json()

def fetch_stock_data(function: str, symbol: str, api_key: str) -> Dict[str,any] | None:
    # 1. Define the parameters for the API request
    params = {
        'function': function,
        'symbol': symbol,
        'apikey': api_key
    }

    try:
        # 2. Make the GET request to the Alpha Vantage API
        response = requests.get(f"{API_URL}", params=params, timeout=10)

        # Raise an exception for bad status codes (4xx and 5xx)
        response.raise_for_status()  # Raise an error for bad responses

        # 3. Parse the JSON response
        data = response.json()

        # 4. Check for API errors in the response
        if "Error Message" in data:
            print("API Error:", data["Error Message"])
            return None

        # Alpha Vantage might return a note about API call frequency limits
        if "Note" in data:
            print("API Note:", data["Note"])
            return None 

        return data

    except requests.exceptions.HTTPError as err_http:
        # Handles errors like 404 Not Found, 500 Server Error
        print(f"HTTP Error occurred: {err_http}")
        print(f"Response Content: {response.text}")
        return None
    except requests.exceptions.ConnectionError as err_conn:
        # Handles errors like DNS failure, refused connection
        print(f"Connection Error occurred: {err_conn}")
        return None
    except requests.exceptions.Timeout as err_timeout:
        # Handles request timeout
        print(f"Timeout Error occurred: {err_timeout}")
        return None
    except requests.exceptions.RequestException as err:
        # Catches all other possible requests errors
        print(f"An unexpected error occurred: {err}")
        return None
    except json.JSONDecodeError:
        # Handles cases where the response is not valid JSON
        print("Error: Could not decode JSON response.")
        return None

if __name__ == "__main__":
    API_KEY = "demo"  # Replace with your actual API key
    symbol = "IBM"
    # data = fetch_stock_data(symbol, API_KEY)
    data = fetch_stock_data("TIME_SERIES_DAILY", symbol, API_KEY)
    print(data)
