public class Twitter {
    // Problem: Design Twitter
    // Design a simplified version of Twitter where users can post tweets, follow/unfollow other users,
    // and retrieve the 10 most recent tweets in their news feed. The news feed includes tweets from
    // users they follow or their own tweets, ordered from most recent to least recent. The operations are:
    // - Twitter(): Constructor, initializes the data structure.
    // - postTweet(userId, tweetId): User posts a new tweet with the given tweetId.
    // - getNewsFeed(userId): Retrieves the 10 most recent tweet IDs in the user's news feed.
    // - follow(followerId, followeeId): Follower starts following followee.
    // - unfollow(followerId, followeeId): Follower stops following followee.

    // DSA Pattern: HashMap, Linked List, and Max-Heap
    // - HashMap: Maps user IDs to User objects, storing follow relationships and tweet lists.
    // - Linked List: Each user has a linked list of their tweets, with the most recent tweet at the head.
    // - Max-Heap: Used in getNewsFeed to merge tweets from followed users, prioritizing the most recent ones.
    // This combination allows efficient tweet posting (O(1)), following/unfollowing (O(1)), and news feed
    // retrieval (O(f * log f) for f followed users).

    // Approach:
    // 1. Data Structures:
    //    - User class: Stores user ID, a set of followed user IDs, and a linked list of tweets (head is most recent).
    //    - Tweet class: Stores tweet ID, timestamp, and a pointer to the next tweet.
    //    - HashMap (userMap): Maps user IDs to User objects for O(1) access.
    //    - Global timestamp: Increments with each tweet to track posting order.
    // 2. Twitter():
    //    - Initialize an empty HashMap to store users.
    // 3. postTweet(userId, tweetId):
    //    - Create a new user if not exists, then add a new tweet to the user's tweet list head.
    // 4. getNewsFeed(userId):
    //    - Get the set of followed users (including self).
    //    - Use a max-heap to store the most recent tweet from each followed user, sorted by timestamp.
    //    - Repeatedly extract the most recent tweet, add its ID to the result, and add the next tweet
    //      from the same user to the heap, until 10 tweets are collected or the heap is empty.
    // 5. follow(followerId, followeeId):
    //    - Create users if they don't exist, then add followeeId to follower's followed set.
    // 6. unfollow(followerId, followeeId):
    //    - If follower exists and follower != followee, remove followeeId from follower's followed set.

    // Key Points to Remember:
    // - Each user follows themselves (set in User constructor) to include their own tweets in their news feed.
    // - Tweets are stored as a linked list per user, with the head being the most recent, allowing O(1) posting.
    // - The max-heap in getNewsFeed merges tweets from multiple users, ensuring the most recent tweets are
    //   retrieved first (based on timestamp).
    // - The global timestamp ensures unique, increasing time values for ordering tweets.
    // - Edge cases are handled:
    //   - If a user doesn't exist, they are created when they post, follow, or are followed.
    //   - If a user has no tweets or follows no one, getNewsFeed returns an empty list.
    //   - Users cannot unfollow themselves to ensure their own tweets appear in their feed.
    // - The implementation is efficient, with constant-time operations for posting and following,
    //   and heap-based merging for news feed retrieval.

    // Time Complexity:
    // - Twitter(): O(1), initializes an empty HashMap.
    // - postTweet(userId, tweetId): O(1)
    //   - HashMap lookup and User creation are O(1).
    //   - Adding a tweet to the head of the linked list is O(1).
    // - getNewsFeed(userId): O(f * log f + k * log f), where f is the number of followed users, k is the number of tweets retrieved (up to 10).
    //   - Building the initial heap with one tweet per followed user: O(f * log f).
    //   - Extracting k tweets and adding next tweets: O(k * log f).
    // - follow(followerId, followeeId): O(1)
    //   - HashMap lookups and HashSet operations are O(1).
    // - unfollow(followerId, followeeId): O(1)
    //   - HashMap lookup and HashSet removal are O(1).

    // Space Complexity: O(u + t + f)
    // - u: Number of users (stored in HashMap, each with a User object).
    // - t: Total number of tweets (stored in linked lists across all users).
    // - f: Total number of follow relationships (stored in HashSet per user).
    // - getNewsFeed uses a heap of size O(f) and an output list of size O(k), where k <= 10.
    // - Overall, space depends on the number of users, tweets, and follow relationships.

    private static int timeStamp = 0; // Global timestamp to track tweet posting order

    // User class to represent each user in Twitter
    private class User {
        int id;                  // Unique user ID
        Set<Integer> followed;   // Set of user IDs this user follows
        Tweet tweetHead;         // Head of the linked list of user's tweets

        // Constructor: Initialize a user with their ID
        public User(int id) {
            this.id = id;
            followed = new HashSet<>(); // Initialize empty set of followed users
            follow(id);                 // User follows themselves
            tweetHead = null;           // Initialize empty tweet list
        }

        // Follow another user
        public void follow(int id) {
            followed.add(id); // Add user ID to followed set (O(1))
        }

        // Unfollow another user
        public void unfollow(int id) {
            if (id != this.id) { // Prevent unfollowing self
                followed.remove(id); // Remove user ID from followed set (O(1))
            }
        }

        // Post a new tweet
        public void post(int id) {
            Tweet newTweet = new Tweet(id); // Create new tweet with given ID
            newTweet.next = tweetHead;      // Link to current head
            tweetHead = newTweet;           // Update head to new tweet (O(1))
        }
    }

    // Tweet class to represent each tweet
    private class Tweet {
        int id;        // Tweet ID
        int time;      // Timestamp of when tweet was posted
        Tweet next;    // Pointer to next tweet in user's tweet list

        // Constructor: Initialize a tweet with its ID
        public Tweet(int id) {
            this.id = id;
            time = timeStamp++; // Assign and increment global timestamp
            next = null;        // Initialize next pointer as null
        }
    }

    private Map<Integer, User> userMap; // Maps user IDs to User objects

    /** Initialize your data structure here. */
    public Twitter() {
        userMap = new HashMap<>(); // Initialize empty HashMap (O(1))
    }

    /** Compose a new tweet. */
    public void postTweet(int userId, int tweetId) {
        // Create user if they don't exist
        if (!userMap.containsKey(userId)) {
            User newUser = new User(userId);
            userMap.put(userId, newUser); // O(1)
        }
        // Post the tweet for the user
        userMap.get(userId).post(tweetId); // O(1)
    }

    /**
     * Retrieve the 10 most recent tweet IDs in the user's news feed.
     * Each item in the news feed must be posted by users who the user followed or by the user themselves.
     * Tweets must be ordered from most recent to least recent.
     */
    public List<Integer> getNewsFeed(int userId) {
        List<Integer> newsFeed = new LinkedList<>(); // Initialize result list
        if (!userMap.containsKey(userId)) {
            return newsFeed; // Return empty list if user doesn't exist
        }

        // Get the set of users followed by userId (includes self)
        Set<Integer> followedUsers = userMap.get(userId).followed;
        // Initialize max-heap to merge tweets by timestamp
        PriorityQueue<Tweet> tweetHeap = new PriorityQueue<>(followedUsers.size(), (a, b) -> b.time - a.time);

        // Add the most recent tweet from each followed user to the heap
        for (int user : followedUsers) {
            Tweet tweet = userMap.get(user).tweetHead;
            if (tweet != null) {
                tweetHeap.add(tweet); // O(log f)
            }
        }

        // Extract up to 10 most recent tweets
        int count = 0;
        while (!tweetHeap.isEmpty() && count < 10) {
            Tweet tweet = tweetHeap.poll(); // Get most recent tweet (O(log f))
            newsFeed.add(tweet.id);         // Add tweet ID to result
            count++;
            // If the user has more tweets, add the next one to the heap
            if (tweet.next != null) {
                tweetHeap.add(tweet.next); // O(log f)
            }
        }

        return newsFeed; // Return the list of tweet IDs
    }

    /** Follower follows a followee. */
    public void follow(int followerId, int followeeId) {
        // Create follower if they don't exist
        if (!userMap.containsKey(followerId)) {
            User newUser = new User(followerId);
            userMap.put(followerId, newUser); // O(1)
        }
        // Create followee if they don't exist
        if (!userMap.containsKey(followeeId)) {
            User newUser = new User(followeeId);
            userMap.put(followeeId, newUser); // O(1)
        }
        // Add followee to follower's followed set
        userMap.get(followerId).follow(followeeId); // O(1)
    }

    /** Follower unfollows a followee. */
    public void unfollow(int followerId, int followeeId) {
        // Check if follower exists and prevent unfollowing self
        if (userMap.containsKey(followerId) && followerId != followeeId) {
            userMap.get(followerId).unfollow(followeeId); // O(1)
        }
    }
}