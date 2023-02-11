package pageUIs;

public class ProductPageUI {
    public static final String ADD_TO_BASKET_BUTTON = "xpath=//button[normalize-space()='Add to basket']";
    public static final String SUCCESS_MESSAGE_TEXT = "xpath=//div[@class='woocommerce-message']";
    public static final String TAB_BY_CLASS = "xpath=//li[contains(@class,'%s')]";
    public static final String TAB_TITLE_TEXT = "xpath=//h2[normalize-space()='%s']";
    public static final String PRODUCT_STOCK_TEXT = "xpath=//p[@class='stock in-stock']";
    public static final String PRODUCT_QUANTITY_TEXTBOX = "xpath=//input[@title='Qty']";
    public static final String PRODUCT_NUMBER_ITEM_TEXT = "xpath=//span[@class='cartcontents']";
    public static final String BASKET_TOTALS_TEXT = "xpath=//h2[normalize-space()='Basket Totals']";
}
