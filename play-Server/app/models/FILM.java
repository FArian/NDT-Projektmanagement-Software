package models;

/**
 * Created by F.Arian on 02.12.17.
 */
public enum FILM {
    AGFA {
        @Override
        public Type getType(int typeNr) {
            for (int i=0;i<Type.values().length;i++) {

                    //return Type.values().clone()[i];


            }
            return null;
        }
    },
    KODAK {
        @Override
        public Type getType(int typeNr) {
            return null;
        }
    };

    public abstract Type getType(int typeNr);

}

