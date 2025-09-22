class Solution {
    // Problem: Group Anagrams
    // Given an array of strings strs, group the anagrams together and return them as a list of
    // lists of strings. An anagram is a word formed by rearranging the letters of another, using
    // all the original letters exactly once. The strings consist of lowercase English letters.

    // DSA Pattern: Hash Map with Sorting
    // This problem is solved using a HashMap to group anagrams by their sorted character sequence.
    // Sorting each string creates a unique key for all anagrams of that string, allowing them to be
    // grouped together efficiently in the HashMap.

    // Approach:
    // 1. Create a HashMap where the key is the sorted string and the value is a list of strings
    //    that are anagrams (i.e., share the same sorted characters).
    // 2. Iterate through each string in strs:
    //    - Convert the string to a char array and sort it.
    //    - Create a string from the sorted char array to use as the key.
    //    - If the key is not in the HashMap, initialize an empty ArrayList for it.
    //    - Add the original string to the list associated with the sorted key.
    // 3. Return the list of lists from the HashMap values, containing the grouped anagrams.

    // Key Points to Remember:
    // - Sorting the characters of a string creates a unique key for all its anagrams.
    // - Use a HashMap to group strings with the same sorted key efficiently.
    // - Convert strings to char arrays for sorting, then back to strings for use as keys.
    // - Handle edge cases like empty input arrays or single-character strings.
    // - The output order of groups or strings within groups does not matter, as per the problem.
    // - All input strings are lowercase, simplifying character handling.
    // - Ensure the HashMap’s value lists are properly initialized to avoid null pointer issues.

    // Time Complexity: O(n * k * log k)
    // - n is the number of strings in strs, and k is the maximum length of any string.
    // - Sorting each string takes O(k log k).
    // - Iterating through n strings and sorting each one takes O(n * k * log k).
    // - HashMap operations (put, get) are O(1) on average.
    // - Converting the HashMap values to a list is O(n) in the worst case.
    // - Overall, the time complexity is dominated by O(n * k * log k).

    // Space Complexity: O(n * k)
    // - The HashMap stores up to n strings, each of length up to k, using O(n * k) space.
    // - The sorted char array and string for each word use O(k) temporary space per iteration.
    // - The output list of lists is required by the problem and stores all input strings, using
    //   O(n * k) space.
    // - Overall, the space complexity is O(n * k), excluding the output.

    public List<List<String>> groupAnagrams(String[] strs) {
        // Initialize HashMap to store sorted string keys and their anagram lists
        Map<String, List<String>> map = new HashMap<>();

        // Iterate through each string in the input array
        for (String word : strs) {
            // Convert the string to a char array for sorting
            char[] chars = word.toCharArray();
            // Sort the characters to create a unique key for anagrams
            Arrays.sort(chars);
            // Create a string from the sorted char array
            String sortedWords = new String(chars);

            // If the sorted key is not in the map, initialize an empty list
            if (!map.containsKey(sortedWords)) {
                map.put(sortedWords, new ArrayList<>());
            }
            // Add the original word to the list for this sorted key
            map.get(sortedWords).add(word);
        }

        // Return the list of anagram groups from the HashMap values
        return new ArrayList<>(map.values());
    }
}