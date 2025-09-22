class TimeMap {
    // Problem: Time Based Key-Value Store
    // Design a time-based key-value store that supports two operations:
    // - set(key, value, timestamp): Stores the key with the given value at the specified timestamp.
    // - get(key, timestamp): Returns the value associated with the key at the largest timestamp
    //   less than or equal to the given timestamp, or an empty string if no such value exists.
    // The key and value are strings, and timestamps are positive integers that are strictly increasing
    // for each key in set operations.

    // DSA Pattern: HashMap with TreeMap (Dictionary with Sorted Timestamps)
    // This problem is solved using a HashMap where each key maps to a TreeMap. The TreeMap stores
    // timestamps as keys and values as strings, leveraging its ability to maintain sorted order and
    // efficiently find the largest timestamp less than or equal to a given timestamp (floor entry).
    // The HashMap provides O(1) access to the TreeMap for a given key, and the TreeMap handles
    // timestamp-based queries efficiently.

    // Approach:
    // 1. Initialize a HashMap where the key is a string and the value is a TreeMap of timestamps
    //    (Integer) to values (String).
    // 2. For set(key, value, timestamp):
    //    - Retrieve or create a TreeMap for the key using computeIfAbsent.
    //    - Insert the timestamp and value into the TreeMap.
    // 3. For get(key, timestamp):
    //    - Retrieve the TreeMap for the key from the HashMap.
    //    - If no TreeMap exists, return an empty string.
    //    - Use TreeMap's floorEntry to find the entry with the largest timestamp less than or
    //      equal to the given timestamp.
    //    - Return the value from the entry if found, or an empty string if no such entry exists.
    // 4. The TreeMap ensures timestamps are sorted, enabling efficient floor queries.

    // Key Points to Remember:
    // - Use a HashMap for O(1) key access and a TreeMap for O(log t) timestamp queries, where t is
    //   the number of timestamps for a key.
    // - The computeIfAbsent method simplifies creating a new TreeMap for a key if it doesn’t exist.
    // - TreeMap’s floorEntry finds the largest timestamp <= given timestamp in O(log t) time.
    // - Return an empty string if the key or timestamp is not found, per problem requirements.
    // - Timestamps are strictly increasing for each key in set operations, ensuring no overwrites
    //   at the same timestamp.
    // - The solution handles multiple values for the same key at different timestamps efficiently.
    // - Edge cases include missing keys, no valid timestamp, or empty TreeMap.

    // Time Complexity:
    // - Initialization (Constructor): O(1), as it creates an empty HashMap.
    // - set(key, value, timestamp): O(log t), where t is the number of timestamps for the key.
    //   - HashMap access is O(1) on average.
    //   - TreeMap insertion (put) is O(log t) due to the balanced tree structure.
    // - get(key, timestamp): O(log t), where t is the number of timestamps for the key.
    //   - HashMap access is O(1) on average.
    //   - TreeMap floorEntry is O(log t) to find the largest timestamp <= given timestamp.
    // - Overall, operations depend on the number of timestamps per key, not the total data size.

    // Space Complexity: O(n * t)
    // - n is the number of unique keys stored in the HashMap.
    // - t is the average number of timestamps per key in the TreeMap.
    // - The HashMap stores n entries, each pointing to a TreeMap.
    // - Each TreeMap stores up to t timestamp-value pairs for its key.
    // - Total space is O(n * t) for all key-timestamp-value mappings.
    // - The output (returned string in get) is required by the problem.

    // Instance variable: HashMap mapping keys to TreeMaps of timestamps and values
    private Map<String, TreeMap<Integer, String>> map;

    // Constructor: Initialize the HashMap
    public TimeMap() {
        map = new HashMap<>();
    }

    // Set operation: Store the value for the key at the given timestamp
    public void set(String key, String value, int timestamp) {
        // Get or create a TreeMap for the key, then add the timestamp-value pair
        map.computeIfAbsent(key, k -> new TreeMap<>()).put(timestamp, value);
    }

    // Get operation: Retrieve the value for the key at the largest timestamp <= given timestamp
    public String get(String key, int timestamp) {
        // Get the TreeMap for the key
        TreeMap<Integer, String> treeMap = map.get(key);

        // If no TreeMap exists for the key, return empty string
        if (treeMap == null) {
            return "";
        }

        // Find the entry with the largest timestamp <= given timestamp
        Map.Entry<Integer, String> entry = treeMap.floorEntry(timestamp);

        // Return the value if found, else return empty string
        return entry == null ? "" : entry.getValue();
    }
}