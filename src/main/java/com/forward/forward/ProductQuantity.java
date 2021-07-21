package com.forward.forward;

public class ProductQuantity
{

    private Product product;
    private int quantity;

    public ProductQuantity(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    public Product getProduct()
    {
        return product;
    }

    public void setProduct(Product product)
    {
        if(product == null)
        {
            throw new IllegalArgumentException("Please provide the necessary information for your product!");
        }
        this.product = product;
    }

    public int getQuantity()
    {
        return quantity;
    }

    public void setQuantity(int quantity)
    {
        if(quantity <= 0)
        {
            throw new IllegalArgumentException("Value not allowed!");
        }
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "ProductQuantity{" +
                "product=" + product +
                ", quantity=" + quantity +
                '}';
    } //made for testing
}
