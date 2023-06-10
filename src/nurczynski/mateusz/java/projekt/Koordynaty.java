package nurczynski.mateusz.java.projekt;

public class Koordynaty {
    private int width;
    private int height;

    public int getWidth()
    {
        return width;
    }

    public int getHeight()
    {
        return height;
    }

    public void setWidth(int width)
    {
        this.width = width;
    }

    public void setHeight(int height)
    {
        this.height = height;
    }

    @Override
    public String toString()
    {
        return "[Szerokosc = "+Integer.toString(width)+", Wysokosc = "+Integer.toString(height)+"]";
    }

    public Koordynaty(int width, int height)
    {
        this.height=height;
        this.width=width;
    }
}
