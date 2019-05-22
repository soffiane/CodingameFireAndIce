public enum BuildingType {

    HQ;

    static public BuildingType get(int type) {

        switch (type) {
            case 0:
                return HQ;
        }
        return null;
    }
}
