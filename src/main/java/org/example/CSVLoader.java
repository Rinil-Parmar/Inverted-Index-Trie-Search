package org.example;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * CSVLoader
 * --------------------------------------------------
 * This class is responsible for reading product data
 * from a CSV file (stored inside the resources folder)
 * and converting each row into a Product object.
 *
 * The CSV file is expected to have column headers like:
 * Product Name, Price, Description, Image URL, Availability, Category, store name
 *
 * Example:
 *  Product Name | Price | Category | ...
 *  -----------------------------------
 *  Cheetos      | $4.79 | Snacks & Candy | ...
 */
public class CSVLoader {

    /**
     * Reads a CSV file and loads product entries into a list.
     * ------------------------------------------------------
     * 1. Opens the CSV file from the project’s resources folder.
     * 2. Reads each row and extracts all product attributes.
     * 3. Creates a Product object for every row.
     * 4. Returns a list of all loaded products.
     *
     * @param filename The CSV file name (must exist in /resources)
     * @return List of Product objects
     */
    public static List<Product> loadProductsFromCSV(String filename) {
        List<Product> products = new ArrayList<>();

        try {
            // Try loading the file from the 'resources' directory
            InputStream inputStream = CSVLoader.class.getClassLoader().getResourceAsStream(filename);

            // If the file cannot be found, show an error and stop loading
            if (inputStream == null) {
                System.err.println("❌ Error: File '" + filename + "' not found in resources folder!");
                return products;
            }

            // Reader and CSVParser handle file decoding and parsing respectively
            Reader reader = new InputStreamReader(inputStream);
            CSVParser csvParser = new CSVParser(reader,
                    CSVFormat.DEFAULT
                            .withFirstRecordAsHeader()   // Skips header row automatically
                            .withIgnoreHeaderCase()      // Makes column names case-insensitive
                            .withTrim()                  // Removes extra spaces
            );

            // Iterate through all rows (records) in the CSV file
            for (CSVRecord record : csvParser) {
                Product product = new Product();

                // Safely extract all column values using helper function
                product.setProductName(getRecordValue(record, "Product Name"));
                product.setPrice(getRecordValue(record, "Price"));
                product.setDescription(getRecordValue(record, "Description"));
                product.setImageUrl(getRecordValue(record, "Image URL"));
                product.setAvailability(getRecordValue(record, "Availability"));
                product.setCategory(getRecordValue(record, "Category"));
                product.setStoreName(getRecordValue(record, "store name"));

                // Add the new product to our collection
                products.add(product);
            }

            // Close parser and reader to free resources
            csvParser.close();
            reader.close();

            System.out.println("✓ Loaded " + products.size() + " products successfully");

        } catch (IOException e) {
            // If file reading or parsing fails, print a clear error message
            System.err.println("⚠️ Error reading CSV file: " + e.getMessage());
        }

        return products;
    }

    /**
     * Safely retrieves a value from a CSV record by column name.
     * ----------------------------------------------------------
     * If the column name doesn’t exist or is misspelled, returns an empty string
     * instead of throwing an exception.
     *
     * @param record     Current row (CSVRecord object)
     * @param columnName Column name to fetch
     * @return String value or "" if missing
     */
    private static String getRecordValue(CSVRecord record, String columnName) {
        try {
            return record.get(columnName);
        } catch (IllegalArgumentException e) {
            // Handles case where column name doesn't exist
            return "";
        }
    }
}
