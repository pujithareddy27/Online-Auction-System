import java.util.*;

class AuctionItem {
    private String itemName;
    private double startingPrice;
    private double highestBid;
    private String highestBidder;

    public AuctionItem(String itemName, double startingPrice) {
        this.itemName = itemName;
        this.startingPrice = startingPrice;
        this.highestBid = startingPrice;
        this.highestBidder = "No bids yet";
    }

    public String getItemName() {
        return itemName;
    }

    public double getHighestBid() {
        return highestBid;
    }

    public String getHighestBidder() {
        return highestBidder;
    }

    public boolean placeBid(String bidderName, double bidAmount) {
        if (bidAmount > highestBid) {
            highestBid = bidAmount;
            highestBidder = bidderName;
            return true;
        }
        return false;
    }
}

class AuctionSystem {
    private List<AuctionItem> auctionItems;

    public AuctionSystem() {
        auctionItems = new ArrayList<>();
    }

    public void addItem(String itemName, double startingPrice) {
        auctionItems.add(new AuctionItem(itemName, startingPrice));
        System.out.println("Item \"" + itemName + "\" has been added with a starting price of $" + startingPrice);
    }

    public void showItems() {
        if (auctionItems.isEmpty()) {
            System.out.println("No items are currently available for auction.");
            return;
        }
        System.out.println("Current items for auction:");
        for (AuctionItem item : auctionItems) {
            System.out.println("- " + item.getItemName() + " | Highest Bid: $" + item.getHighestBid() + " | Highest Bidder: " + item.getHighestBidder());
        }
    }

    public void placeBid(String itemName, String bidderName, double bidAmount) {
        for (AuctionItem item : auctionItems) {
            if (item.getItemName().equalsIgnoreCase(itemName)) {
                if (item.placeBid(bidderName, bidAmount)) {
                    System.out.println(bidderName + " placed a successful bid of $" + bidAmount + " on \"" + itemName + "\".");
                } else {
                    System.out.println("Bid amount must be higher than the current highest bid.");
                }
                return;
            }
        }
        System.out.println("Item \"" + itemName + "\" not found.");
    }
}

public class Main {
    public static void main(String[] args) {
        AuctionSystem auctionSystem = new AuctionSystem();

        // Add items to auction
        auctionSystem.addItem("Antique Vase", 100.0);
        auctionSystem.addItem("Vintage Car", 50000.0);

        // Display items
        auctionSystem.showItems();

        // Place bids
        auctionSystem.placeBid("Antique Vase", "Alice", 150.0);
        auctionSystem.placeBid("Vintage Car", "Bob", 55000.0);
        auctionSystem.placeBid("Antique Vase", "Charlie", 140.0);  // Bid too low

        // Display items after bidding
        auctionSystem.showItems();
    }
}