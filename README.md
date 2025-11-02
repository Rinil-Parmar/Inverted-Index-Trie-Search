# Inverted Index Product Search System

A Java Maven application that implements Trie-based inverted indexing for fast product search.

## Features

- Fast word-based search using Trie data structure
- Searches across product name, category, and store name
- Case-insensitive search
- O(m) search time complexity (m = word length)

## Prerequisites

- Java 11 or higher
- Apache Maven 3.6+

## Project Structure

```
inverted-index-search/
├── pom.xml
├── src/
│   └── main/
│       ├── java/org/example/
│       │   ├── Main.java
│       │   ├── TrieNode.java
│       │   ├── Trie.java
│       │   ├── Product.java
│       │   ├── InvertedIndex.java
│       │   └── CSVLoader.java
│       └── resources/
│           └── products.csv
```

## Setup Instructions

1. **Create resources folder**
```bash
mkdir -p src/main/resources
```

2. **Add products.csv to resources folder**

CSV format:
```csv
Product Name,Price,Description,Image URL,Availability,Category,store name
Dell Laptop XPS 15,$1299.99,High performance,https://url,In-stock,Electronics,TechMart
Cheetos,$4.79,Snack food,https://url,In-stock,Snacks & Chips,FreshCo
```

3. **Build the project**
```bash
mvn clean install
```

4. **Run the application**
```bash
mvn exec:java -Dexec.mainClass="com.product.search.Main"
```

## Usage

```
=== INVERTED INDEX - PRODUCT SEARCH SYSTEM ===
Loading products from file...
✓ Loaded 15 products successfully
✓ Built inverted index with 45 unique words
---------------------------------------------------
Enter search word (or 'exit' to quit): laptop
---------------------------------------------------
Search Results for "laptop":
Found in 3 document(s)

Document 1:
  Product Name: Dell Laptop XPS 15
  Price: $1299.99
  Store: TechMart
---------------------------------------------------
Enter search word (or 'exit' to quit): exit
Thank you for using the Product Search System!
```

## Complexity Analysis

| Operation | Time Complexity |
|-----------|----------------|
| Insert | O(m) |
| Search | O(m) |
| Build Index | O(N × L) |

m = word length, N = number of documents, L = average words per document

## Troubleshooting

**File not found error:**
- Ensure products.csv is in `src/main/resources/` folder

**Maven not installed:**
```bash
# Windows
choco install maven

# Mac
brew install maven

# Linux
sudo apt-get install maven
```

## Author
Rinil Parmar
