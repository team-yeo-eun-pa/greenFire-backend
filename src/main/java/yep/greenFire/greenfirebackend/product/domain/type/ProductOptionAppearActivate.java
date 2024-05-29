package yep.greenFire.greenfirebackend.product.domain.type;

public enum ProductOptionAppearActivate {

    USABLE("usable"),
    DISABLE("disable"),
    DELETED("deleted");

    private final String optionAppearActivate;

    ProductOptionAppearActivate(String optionAppearActivate) {
        this.optionAppearActivate = optionAppearActivate;
    }

    public String getOptionAppearActivate() {
        return optionAppearActivate;
    }

}
