#!/usr/bin/env python3

# The basics
# open() is used to open a file
# read() reads the contents of the file
# write() writes to the file
# close() closes the file, if you use 'with' statement, it is closed automatically. Prefer using 'with' statement.
import csv

import pandas as pd

def write_to_file_unsafe():
    file = open("data.txt", "w")  # Open a file in write mode
    try:
        file.write("Hello, World!\n")  # Write to the file
        file.write("This is a test file.\n")
    finally:
        file.close() # Might be missed or skipped on error

# Safe way using 'with' statement
def write_to_file_safe():
    with open("data.txt", "w", encoding="utf-8") as file:  # Open a file in write mode
        file.write("Hello, World!\n")  # Write to the file
        file.write("This is a test file.\n")
    # File is automatically closed when exiting the 'with' block

def write_to_file_safe_with_error_handling():
    try:
        with open("data.txt", "a") as file:  # Open a file in write mode
            file.write("Hello, World!\n")  # Write to the file
            file.write("This is a test file.\n")
    except IOError as e:
        print(f"User is NOT allowed to write to file: {e}")
    # File is automatically closed when exiting the 'with' block

def read_from_file_safe_with_error_handling():
    try:
        with open("nvda_us_d.csv", "r", encoding="utf-8") as file:  # Open a file in read mode
            # content = file.read()  # Read the contents of the file
            # print(content)

            stock_reader = csv.reader(file)
            for row in stock_reader:
                date, open_price, high, low, close, volume = row
                print(f"Date: {date}, Open: {open_price}, High: {high}, Low: {low}, Close: {close}, Volume: {volume}")

    except IOError as e:
        print(f"User is NOT allowed to read file: {e}")
    # File is automatically closed when exiting the 'with' block

def main():
    # write_to_file_unsafe()
    # write_to_file_safe()
    # write_to_file_safe_with_error_handling()
    # read_from_file_safe_with_error_handling()

    # Using pandas to read CSV file
    try:
        df = pd.read_csv("nvda_us_d.csv")
        print(df)  # Print the first few rows of the DataFrame
    except Exception as e:
        print(f"Error reading CSV file with pandas: {e}")

if __name__ == "__main__":
    main()
