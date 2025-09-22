// Problem: Hand of Straights
// Given an array of integers 'hand' and an integer 'groupSize', determine if the cards in 'hand' can be rearranged
// into groups of 'groupSize' consecutive cards (e.g., [1,2,3] for groupSize=3).
// Return true if such an arrangement is possible, false otherwise.

// DSA Pattern: Greedy / TreeMap
// This problem is solved using a greedy approach by leveraging a TreeMap to store card counts and process cards
// in ascending order, ensuring consecutive groups can be formed.

// Approach:
// 1. Check if the length of 'hand' is divisible by 'groupSize'. If not, return false.
// 2. Use a TreeMap to store the frequency of each card in 'hand' (sorted by card value).
// 3. While the TreeMap is not empty:
//    - Get the smallest card (firstKey) to start a group.
//    - For each of the 'groupSize' consecutive cards (starting from the smallest):
//      - Check if the current card exists in the TreeMap.
//      - If it does not exist, return false (group cannot be formed).
//      - Decrease the card's count in the TreeMap, removing it if the count becomes 0.
// 4. If all groups are successfully formed, return true.

// Key Points to Remember:
// - The TreeMap ensures cards are processed in ascending order, which is critical for forming consecutive groups.
// - Each group must consist of exactly 'groupSize' consecutive cards.
// - Early exit if the hand length is not divisible by groupSize.
// - Edge cases:
//   - Empty hand or groupSize=0: handled by length check.
//   - Single card with groupSize>1: returns false due to divisibility check.
//   - No consecutive cards available: returns false when a required card is missing.
// - The solution assumes non-negative integers in the hand array.

// Time Complexity: O(n log n), where n is the length of hand
// - Building the TreeMap takes O(n log n) for inserting n elements.
// - Each card is processed at most once, with O(log n) for TreeMap operations (get, put, remove).
// - The loop iterates O(n/groupSize) times, with O(groupSize * log n) for each group.
// - Overall, the dominant factor is O(n log n) due to TreeMap operations.

// Space Complexity: O(n)
// - The TreeMap stores at most n entries, requiring O(n) space.
// - No additional significant space is used beyond the input array and TreeMap.

// Constraints:
// - 1 <= hand.length <= 10^4
// - 0 <= hand[i] <= 10^9
// - 1 <= groupSize <= hand.length

public class Solution {
    public boolean isNStraightHand(int[] hand, int groupSize) {
        // Check if hand length is divisible by groupSize
        if (hand.length % groupSize != 0) return false;
        
        // Store card frequencies in a TreeMap
        TreeMap<Integer, Integer> cardCounts = new TreeMap<>();
        for (int card : hand) {
            cardCounts.put(card, cardCounts.getOrDefault(card, 0) + 1);
        }
        
        // Process groups of consecutive cards
        while (!cardCounts.isEmpty()) {
            int first = cardCounts.firstKey(); // Get smallest card
            for (int i = 0; i < groupSize; i++) {
                int currentCard = first + i;
                // Check if current card exists
                if (!cardCounts.containsKey(currentCard)) {
                    return false;
                }
                // Update or remove card count
                int count = cardCounts.get(currentCard);
                if (count == 1) {
                    cardCounts.remove(currentCard);
                } else {
                    cardCounts.put(currentCard, count - 1);
                }
            }
        }
        
        // Return true if all groups are formed
        return true;
    }
}