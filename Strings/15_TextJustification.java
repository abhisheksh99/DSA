class Solution {
    // Problem: Text Justification
    // Given an array of strings words and an integer maxWidth, format the text such that each
    // line has exactly maxWidth characters and is fully (left and right) justified. Pack as
    // many words as possible in each line, distributing extra spaces as evenly as possible
    // between words. The last line and lines with one word are left-justified with no extra
    // spaces between words. Return the formatted lines as a list of strings.
    // The words consist of lowercase English letters, and maxWidth is a positive integer.

    // DSA Pattern: String Manipulation and Greedy
    // This problem is solved by greedily packing words into lines while ensuring each line is
    // exactly maxWidth characters. We process the words sequentially, determine how many fit
    // in a line, and then justify the line by distributing spaces appropriately. Special
    // handling is required for the last line or lines with a single word.

    // Approach:
    // 1. Initialize an empty list to store the justified lines.
    // 2. Iterate through the words array using an index to track the current word:
    //    - For each line, calculate how many words can fit within maxWidth, accounting for
    //      at least one space between words.
    //    - Track the total character count (including spaces) and the last word index for
    //      the current line.
    // 3. For each line:
    //    - If it’s the last line or contains only one word, left-justify by appending words
    //      with single spaces and padding the rest with spaces.
    //    - Otherwise, calculate the total spaces needed and distribute them evenly between
    //      words, with extra spaces added to earlier gaps if uneven.
    //    - Build the line using a StringBuilder and add it to the result list.
    // 4. Update the index to the next unprocessed word and continue until all words are used.
    // 5. Return the list of justified lines.

    // Key Points to Remember:
    // - Greedily pack as many words as possible in each line while respecting maxWidth.
    // - For normal lines, distribute spaces evenly between words; extra spaces go to earlier gaps.
    // - For the last line or single-word lines, left-justify with single spaces and pad with spaces.
    // - Use StringBuilder for efficient string construction.
    // - Account for the minimum one-space gap between words when calculating line length.
    // - Handle edge cases: single word, last line, or maxWidth exactly matching word lengths.
    // - The output lines must be exactly maxWidth characters, including spaces.

    // Time Complexity: O(n * m)
    // - n is the number of words in the input array.
    // - m is the average length of words or maxWidth (for space padding).
    // - Iterating through words to determine line breaks is O(n).
    // - For each line, building the justified string involves appending words and spaces,
    //   which takes O(m) time per line in the worst case.
    // - Total time is O(n * m) where m is bounded by maxWidth or word lengths.
    // - Edge case checks are O(1).

    // Space Complexity: O(maxWidth + k)
    // - StringBuilder stores at most maxWidth characters per line, so O(maxWidth).
    // - The result list stores k lines, where k is the number of lines (k <= n).
    // - Excluding the output, the space for StringBuilder is O(maxWidth).
    // - The output list is required by the problem.

    public List<String> fullJustify(String[] words, int maxWidth) {
        // Initialize result list to store justified lines
        List<String> result = new ArrayList<>();
        // Start with the first word
        int index = 0;

        // Process all words
        while (index < words.length) {
            // Initialize count with the length of the first word in the line
            int count = words[index].length();
            // Track the index of the next word after the current line
            int last = index + 1;

            // Determine how many words can fit in the current line
            while (last < words.length) {
                // Check if adding the next word (with a space) exceeds maxWidth
                if (count + 1 + words[last].length() > maxWidth) break;
                // Add the space and word length to the count
                count += 1 + words[last].length();
                last++;
            }

            // Initialize StringBuilder to build the current line
            StringBuilder line = new StringBuilder();
            // Number of words in the current line
            int numberOfWords = last - index;

            // Handle last line or single-word line (left-justified)
            if (last == words.length || numberOfWords == 1) {
                // Append words with single spaces between them
                for (int i = index; i < last; i++) {
                    line.append(words[i]);
                    if (i < last - 1) line.append(" ");
                }
                // Pad the rest with spaces to reach maxWidth
                while (line.length() < maxWidth) line.append(" ");
            } else {
                // Handle fully justified lines
                // Calculate total spaces needed (maxWidth minus word lengths plus gaps)
                int totalSpaces = maxWidth - count + (numberOfWords - 1);
                // Calculate spaces to place between each pair of words
                int spacesBetweenWords = totalSpaces / (numberOfWords - 1);
                // Calculate extra spaces to distribute to earlier gaps
                int extraSpaces = totalSpaces % (numberOfWords - 1);

                // Build the line by appending words and spaces
                for (int i = index; i < last - 1; i++) {
                    line.append(words[i]);
                    // Add the calculated number of spaces
                    line.append(" ".repeat(spacesBetweenWords));
                    // Add one extra space if there are any remaining
                    if (extraSpaces > 0) {
                        line.append(" ");
                        extraSpaces--;
                    }
                }
                // Append the last word of the line (no trailing spaces)
                line.append(words[last - 1]);
            }

            // Add the justified line to the result
            result.add(line.toString());
            // Move to the next unprocessed word
            index = last;
        }

        // Return the list of justified lines
        return result;
    }
}