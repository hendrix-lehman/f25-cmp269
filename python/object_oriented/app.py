#!/usr/bin/env python3

# Python supports the core principles of Object-Oriented Programming (OOP), which include:
# 1. Encapsulation
# 2. Inheritance
# 3. Polymorphism

# Defining a simple class to demonstrate OOP principles
class Book:
    def __init__(self, title, author, year):
        self.title = title
        self.author = author
        self.year = year

    def get_info(self):
        return f"{self.title} by {self.author}, published in {self.year}"

# Inheritance: Creating a subclass that inherits from Book
class EBook(Book):
    def __init__(self, title, author, year, file_size):
        super().__init__(title, author, year)
        self.file_size = file_size  # in MB

    # Polymorphism: Overriding the get_info method
    def get_info(self):
        base_info = super().get_info()
        return f"{base_info}, File Size: {self.file_size}MB"


if __name__ == "__main__":
    # Creating instances of Book and EBook
    physical_book = Book("1984", "George Orwell", 1949)
    ebook = EBook("Digital Fortress", "Dan Brown", 1998, 2.5)

    # Displaying information using the get_info method
    print(physical_book.get_info())
    print(ebook.get_info())





