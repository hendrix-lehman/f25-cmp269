#!/usr/bin/env python3

import math
import sys

def main():

    print("Hello", "World!", "from App.py", sep="*", end="aaaa")
    print("This is a sample application.")
    print(3)
    print(3.14 + 2.71)
    print("abc" + "def")
    print("5 + 10 =", 5 + 10)

    # print(3.14 + "abc")  # This will raise a TypeError

    print(2 * 3)
    print(2 ** 3)

    div = 7 / 3
    print(div)
    print(type(div))

    val = 7 // 3
    print(val)
    print(type(val))

    remainder = 7.0 % 3
    print(remainder)
    print(type(remainder))

    # computing with numbers 
    # num = eval(input("Enter a number: ")) # eval is EVIL, be cautious. 
    num = int(input("Enter a number: ")) # eval is EVIL, be cautious. 
    print("You entered:", num)
    type_num = type(num)
    print("Type of the entered value:", type_num)
    print(num + abs(num))  # This will concatenate the strings

    # using the math library
    angle = math.pi / 4  # 45 degrees in radians
    print("cos(45 degrees) =", math.cos(angle))

    # explicit type conversion
    int_value = int(3.99)
    print("Integer value of 3.99 is", int_value)
    print("Type of int_value:", type(int_value))

    float_value = float("3.14")
    print("Float value of '3.14' is", float_value)
    print("Type of float_value:", type(float_value))

    print(sys.getsizeof(0))  # size of integer in bytes
    print(sys.getsizeof(0.0))  # size of float in bytes

if __name__ == "__main__":
    main()
