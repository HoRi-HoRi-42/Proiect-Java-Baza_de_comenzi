package businessLogic.validators;

import model.Produs;

public class StocVerificator implements Validator<Produs> {


    @Override
    public void validate(Produs p) {
        if (p.getCantitate() < 0)
            throw new IllegalArgumentException("Nu mai este produsul:" + p.getNume_produs());
        else if (p.getCantitate() == 0)
            throw new IllegalArgumentException("Acesta este ultimul produs: " + p.getNume_produs());

    }
}
