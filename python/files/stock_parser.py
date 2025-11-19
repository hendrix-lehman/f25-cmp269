#!/usr/bin/env python3

import csv
import sys

def read_stock_data(file):
    try:
        with open(file, "r", encoding="utf-8") as f:  # Open a file in read mode
            stock_reader = csv.reader(f)
            for row in stock_reader:
                date, open_price, high, low, close, volume = row
                print(f"Date: {date}, Open: {open_price}, High: {high}, Low: {low}, Close: {close}, Volume: {volume}")
    except IOError as e:
        print(f"User is NOT allowed to read file: {e}")
    # File is automatically closed when exiting the 'with' block w

def main():
    if len(sys.argv) != 2:
        print("Usage: python stock_parser.py <stock_data_file.csv>")
        sys.exit(1)

    file = sys.argv[1]
    print(f"Reading stock data from {file}")
    read_stock_data(file)

if __name__ == "__main__":
    main()
