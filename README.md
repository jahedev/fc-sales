
#  "Foreign Currency Sales" Program

_Mohammed J. Hossain_

This is a class project in which I created a program which takes the records of:
any **sale** made with any **foreign currency**, in any date, and displays the **total amount** in US Dollars.

### Description:

+ The currency is selected by using ISO-4217 currency codes. Click [here](https://en.wikipedia.org/wiki/ISO_4217) for all currency codes.
+ The currencies are provided by https://www.exchangerate-api.com API.
+ The price any value greater than 0.
+ The date when the sale took place.
+ 2 date range fields on the top to display only sales taken place within a specific date range.
+ Whenever a record is added, the total sum of sales is calculated in US Dollars in the bottom right corner.
+ A custom JList renders all records on screen.

### Screenshot
[![App Demo Video](https://i.imgur.com/6d2PZHa.png)](http://www.youtube.com/watch?v=nT0Ofgn4aZ4 "Foreign Currency Sales App Demo")


### How to Run:
_(Requires Java 10 or later)_
```
javac CurrencySalesApp.java CurrencyUtil.java Sale.java
java CurrencySalesApp
```

### Example Input:
```
CAD | 3200.50 | 9/9/2017
GBP | 8612.9  | 5/13/2020
```
