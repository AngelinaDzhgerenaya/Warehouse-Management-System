package org.website.myproject.routes;

public class WarehouseRoutes {
    public final static String ROOT = "/api";

    public final static String CATEGORIES = ROOT + "/categories";
    public final static String CATEGORIESID = CATEGORIES + "/{id}";

    public final static String PRODUCTS = ROOT + "/products";
    public final static String PRODUCTSID = PRODUCTS + "/{id}";

    public final static String WAREHOUSES = ROOT + "/warehouses";
    public final static String WAREHOUSESID = WAREHOUSES + "/{id}";

    public final static String STOCKS = ROOT + "/stocks";
    public final static String STOCKSID = STOCKS + "/{id}";
    public final static String STOCKSINCREASE = STOCKS + "/increase";
    public final static String STOCKSDECREASE = STOCKS + "/decrease";

}
