package yep.greenFire.greenfirebackend.product.domain.type;

public enum ProductOptionAppearActivate {

    Y("y"),
    N("n");

    private final String optionAppearActivate;

    ProductOptionAppearActivate(String optionAppearActivate) {
        this.optionAppearActivate = optionAppearActivate;
    }

    public String getOptionAppearActivate() {
        return optionAppearActivate;
    }

}
