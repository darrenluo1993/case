package pers.darren.java8.stream;

/**
 * 排管
 *
 * @CreatedBy Darren Luo
 * @CreatedTime 4/21/22 9:35 AM
 */
public class Calandria {
    /**
     * 型号
     */
    private String model;
    /**
     * 型号名称
     */
    private String modelNm;
    /**
     * 型号层级
     */
    private String modelLvl;

    public Calandria(String model) {
        this.model = model;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getModelNm() {
        return modelNm;
    }

    public void setModelNm(String modelNm) {
        this.modelNm = modelNm;
    }

    public String getModelLvl() {
        return modelLvl;
    }

    public void setModelLvl(String modelLvl) {
        this.modelLvl = modelLvl;
    }

    @Override
    public String toString() {
        return "Calandria{" + "model='" + model + '\'' + ", modelNm='" + modelNm + '\'' + ", modelLvl='" + modelLvl + '\'' + '}';
    }
}
