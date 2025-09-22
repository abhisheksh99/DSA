class Solution {
    // Problem: Encode and Decode Strings
    // Design an algorithm to encode a list of strings to a string. The encoded string is then
    // sent over the network and is decoded back to the original list of strings.
    // The encoding must be such that the decoded list matches the original list exactly.
    // The strings consist of printable ASCII characters.

    // DSA Pattern: String Manipulation with Delimiters
    // This problem is solved using string manipulation where each string is prefixed with its
    // length followed by a delimiter ('#'). This allows unambiguous decoding by parsing the
    // length, skipping the delimiter, and extracting the exact string length, handling any
    // characters (including '#') without ambiguity.

    // Approach for Encode:
    // 1. Initialize a StringBuilder to efficiently build the encoded string.
    // 2. Iterate through each string in the list:
    //    - Append the length of the string as a string, followed by '#'.
    //    - Append the original string.
    // 3. Return the final StringBuilder as a string.

    // Approach for Decode:
    // 1. Initialize an empty list to store the decoded strings.
    // 2. Initialize an index i to traverse the encoded string.
    // 3. While i < encoded string length:
    //    - Find the index j of the next '#' starting from i.
    //    - Parse the substring from i to j as the length (integer).
    //    - Extract the string of that length starting after the '#' (j + 1 to j + 1 + length).
    //    - Add the extracted string to the result list.
    //    - Update i to j + 1 + length for the next iteration.
    // 4. Return the result list.

    // Key Points to Remember:
    // - Prefixing each string with its length and a delimiter ('#') allows decoding without
    //   knowing string boundaries in advance.
    // - Use StringBuilder for efficient string concatenation in the encode method.
    // - In decode, use indexOf to locate delimiters and substring for extraction.
    // - The length prefix ensures we extract exactly the right number of characters, handling
    //   strings containing '#' or other special characters.
    // - Handle edge cases like empty list (empty string), single string, or strings of length 0.
    // - Assume printable ASCII characters; no need for URL encoding or similar.
    // - The delimiter '#' must not conflict with string content; length prefix resolves this.
    // - Ensure i updates correctly to avoid infinite loops or index out-of-bounds.

    // Time Complexity:
    // - Encode: O(total_length), where total_length is the sum of lengths of all strings plus
    //   the lengths of the numeric prefixes and delimiters (O(1) per string).
    // - Decode: O(total_length), as indexOf and substring operations are linear in the string
    //   length traversed, and we process the entire string once.
    // - Overall, both methods are O(total_length).

    // Space Complexity: O(total_length)
    // - Encode: StringBuilder uses O(total_length) space for the output string.
    // - Decode: Result list uses O(total_length) space for the output strings, plus O(1)
    //   temporary space for substring operations.
    // - Overall, O(total_length) excluding the output (but output is required).

    public String encode(List<String> strs) {
        // Initialize StringBuilder for efficient string building
        StringBuilder sb = new StringBuilder();
        
        // Iterate through each string in the list
        for (String s : strs) {
            // Append length of string + '#' delimiter + the string itself
            sb.append(s.length()).append('#').append(s);
        }
        
        // Return the encoded string
        return sb.toString();
    }

    public List<String> decode(String s) {
        // Initialize result list for decoded strings
        List<String> result = new ArrayList<>();
        // Initialize index to traverse the encoded string
        int i = 0;
        
        // Traverse the encoded string
        while (i < s.length()) {
            // Find the index of the next '#' delimiter
            int j = s.indexOf('#', i);
            // Parse the length from i to j
            int length = Integer.parseInt(s.substring(i, j));
            // Extract the string of that length after the delimiter
            result.add(s.substring(j + 1, j + 1 + length));
            // Update index for next iteration
            i = j + 1 + length;
        }
        
        // Return the decoded list
        return result;
    }
}