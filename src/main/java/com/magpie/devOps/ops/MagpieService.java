package com.magpie.devOps.ops;

public interface MagpieService {

    public void update(Magpie magpie);

    public String queryByDay(MagpieForm param);

    public String getDescs();
    
    public void sendMail();
}
