package models;

/**
 * Created by F.Arian on 01.12.17.
 */
public enum NAME {
    GOTOTYPE{
        @Override
        public Type getType(Type type) {
            for(int i=0;i<Type.values().length;i++){
                if(Type.values()[i].equals(type)){
                    return Type.values().clone()[i];
                }
            }
            return Type.RT_CAMERA;
        }
    },
    SENTINEL{
        @Override
        public Type getType(Type type) {
            for(int i=0;i<Type.values().length;i++){
                if(Type.values()[i].equals(type)){
                    return Type.values().clone()[i];
                }
            }
            return Type.RT_CAMERA;
        }
    },
    GAMMAMAT{
        @Override
        public Type getType(Type type) {
            for(int i=0;i<Type.values().length;i++){
                if(Type.values()[i].equals(type)){
                    return Type.values().clone()[i];
                }
            }
            return Type.RT_CAMERA;
        }
    },
    Dandong_XY_Electric{
        @Override
        public Type getType(Type type) {
            for(int i=0;i<Type.values().length;i++){
                if(Type.values()[i].equals(type)){
                    return Type.values().clone()[i];
                }
            }
            return Type.RT_CAMERA;
        }
    },
    OSERIX{
        @Override
        public Type getType(Type type) {
            for(int i=0;i<Type.values().length;i++){
                if(Type.values()[i].equals(type)){
                    return Type.values().clone()[i];
                }
            }
            return Type.RT_CAMERA;
        }
    },AGFA{
        @Override
        public Type getType(Type type) {
            for(int i=0;i<Type.values().length;i++){
                if(Type.values()[i].equals(type)){
                    return Type.values().clone()[i];
                }
            }
            return null;
            
        }
    },KODAK{
        @Override
        public Type getType(Type type) {
            for(int i=0;i<Type.values().length;i++){
                if(Type.values()[i].equals(type)){
                    return Type.values().clone()[i];
                }
            }
            return null;
        }
    },FUJIFILM{
        @Override
        public Type getType(Type type) {
            for(int i=0;i<Type.values().length;i++){
                if(Type.values()[i].equals(type)){
                    return Type.values().clone()[i];
                }
            }
            return null;
        }
    },
    NDT{
        @Override
        public Type getType(Type type) {
            for(int i=0;i<Type.values().length;i++){
                if(Type.values()[i].equals(type)){
                    return Type.values().clone()[i];
                }
            }
            return null;
        }
    };

    public abstract Type getType(Type type);


}
