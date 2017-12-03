package models;

/**
 * Created by F.Arian on 01.12.17.
 */
public enum MODEL {
    SIGMA_880 {
        @Override
        public DESCRIPTION getDescription(DESCRIPTION description) {
            for (int i = 0; i < DESCRIPTION.values().length; i++) {
                if (DESCRIPTION.values()[i].equals(description)) {
                    return DESCRIPTION.values().clone()[i];
                }
            }
            return null;
        }
    }, OMEGA_880 {
        @Override
        public DESCRIPTION getDescription(DESCRIPTION description) {
            for (int i = 0; i < DESCRIPTION.values().length; i++) {
                if (DESCRIPTION.values()[i].equals(description)) {
                    return DESCRIPTION.values().clone()[i];
                }
            }
            return null;
        }
    }, DELTA_880 {
        @Override
        public DESCRIPTION getDescription(DESCRIPTION description) {
            for (int i = 0; i < DESCRIPTION.values().length; i++) {
                if (DESCRIPTION.values()[i].equals(description)) {
                    return DESCRIPTION.values().clone()[i];
                }
            }
            return null;
        }
    }, ELITA_880 {
        @Override
        public DESCRIPTION getDescription(DESCRIPTION description) {
            for (int i = 0; i < DESCRIPTION.values().length; i++) {
                if (DESCRIPTION.values()[i].equals(description)) {
                    return DESCRIPTION.values().clone()[i];
                }
            }
            return null;
        }
    }, SCARPRO_1075_880 {
        @Override
        public DESCRIPTION getDescription(DESCRIPTION description) {
            for (int i = 0; i < DESCRIPTION.values().length; i++) {
                if (DESCRIPTION.values()[i].equals(description)) {
                    return DESCRIPTION.values().clone()[i];
                }
            }
            return null;
        }
    }, YG_192_S {
        @Override
        public DESCRIPTION getDescription(DESCRIPTION description) {
            for (int i = 0; i < DESCRIPTION.values().length; i++) {
                if (DESCRIPTION.values()[i].equals(description)) {
                    return DESCRIPTION.values().clone()[i];
                }
            }
            return null;
        }
    }, Exertus_Dual_120 {
        @Override
        public DESCRIPTION getDescription(DESCRIPTION description) {
            for (int i = 0; i < DESCRIPTION.values().length; i++) {
                if (DESCRIPTION.values()[i].equals(description)) {
                    return DESCRIPTION.values().clone()[i];
                }
            }
            return null;
        }
    }, Exertus_Dual_60 {
        @Override
        public DESCRIPTION getDescription(DESCRIPTION description) {
            for (int i = 0; i < DESCRIPTION.values().length; i++) {
                if (DESCRIPTION.values()[i].equals(description)) {
                    return DESCRIPTION.values().clone()[i];
                }
            }
            return null;
        }
    }, EXERTUS_CIRCA_80 {
        @Override
        public DESCRIPTION getDescription(DESCRIPTION description) {
            for (int i = 0; i < DESCRIPTION.values().length; i++) {
                if (DESCRIPTION.values()[i].equals(description)) {
                    return DESCRIPTION.values().clone()[i];
                }
            }
            return null;
        }
    }, EXERTUS_CIRCA_120 {
        @Override
        public DESCRIPTION getDescription(DESCRIPTION description) {
            for (int i = 0; i < DESCRIPTION.values().length; i++) {
                if (DESCRIPTION.values()[i].equals(description)) {
                    return DESCRIPTION.values().clone()[i];
                }
            }
            return null;
        }
    },
    EXERTUS_RID_Se4P {
        @Override
        public DESCRIPTION getDescription(DESCRIPTION description) {
            for (int i = 0; i < DESCRIPTION.values().length; i++) {
                if (DESCRIPTION.values()[i].equals(description)) {
                    return DESCRIPTION.values().clone()[i];
                }
            }
            return null;
        }
    }, EXERTUS_VOX_100 {
        @Override
        public DESCRIPTION getDescription(DESCRIPTION description) {
            for (int i = 0; i < DESCRIPTION.values().length; i++) {
                if (DESCRIPTION.values()[i].equals(description)) {
                    return DESCRIPTION.values().clone()[i];
                }
            }
            return null;
        }
    }, EXERTUS_VOX_400 {
        @Override
        public DESCRIPTION getDescription(DESCRIPTION description) {
            for (int i = 0; i < DESCRIPTION.values().length; i++) {
                if (DESCRIPTION.values()[i].equals(description)) {
                    return DESCRIPTION.values().clone()[i];
                }
            }
            return null;
        }
    }, EXERTUS_LIGHT_W {
        @Override
        public DESCRIPTION getDescription(DESCRIPTION description) {
            for (int i = 0; i < DESCRIPTION.values().length; i++) {
                if (DESCRIPTION.values()[i].equals(description)) {
                    return DESCRIPTION.values().clone()[i];
                }
            }
            return null;
        }
    }, EXERTUS_LIGHT {
        @Override
        public DESCRIPTION getDescription(DESCRIPTION description) {
            for (int i = 0; i < DESCRIPTION.values().length; i++) {
                if (DESCRIPTION.values()[i].equals(description)) {
                    return DESCRIPTION.values().clone()[i];
                }
            }
            return null;
        }
    },
    ROLL {
        @Override
        public DESCRIPTION getDescription(DESCRIPTION description) {
            for (int i = 0; i < DESCRIPTION.values().length; i++) {
                if (DESCRIPTION.values()[i].equals(description)) {
                    return DESCRIPTION.values().clone()[i];
                }
            }
            return null;
        }
    },
    SHEET {
        @Override
        public DESCRIPTION getDescription(DESCRIPTION description) {
            for (int i = 0; i < DESCRIPTION.values().length; i++) {
                if (DESCRIPTION.values()[i].equals(description)) {
                    return DESCRIPTION.values().clone()[i];
                }
            }
            return null;
        }
    };

    public enum DESCRIPTION {
        AGFA_D7_FILM_VACUPAC_PB_BOX_100_SHEETS {
            @Override
            public SIZE getSize(SIZE size) {
                for (int i = 0; i < SIZE.values().length; i++) {
                    if (SIZE.values()[i].equals(size)) {
                        return SIZE.values().clone()[i];
                    }
                }
                return null;
            }
        },
        AGFA_D7_FILM_ETE_PB_BOX_100_SHEETS {
            @Override
            public SIZE getSize(SIZE size) {
                for (int i = 0; i < SIZE.values().length; i++) {
                    if (SIZE.values()[i].equals(size)) {
                        return SIZE.values().clone()[i];
                    }
                }
                return null;
            }
        },
        AGFA_D7_FILM_ETE_DW_BOX_100_SHEETS {
            @Override
            public SIZE getSize(SIZE size) {
                for (int i = 0; i < SIZE.values().length; i++) {
                    if (SIZE.values()[i].equals(size)) {
                        return SIZE.values().clone()[i];
                    }
                }
                return null;
            }
        }, AGFA_D7_FILM_NIF_BOX_100_SHEETS {
            @Override
            public SIZE getSize(SIZE size) {
                for (int i = 0; i < SIZE.values().length; i++) {
                    if (SIZE.values()[i].equals(size)) {
                        return SIZE.values().clone()[i];
                    }
                }
                return null;
            }
        }, AGFA_D7_FILM_FW_BOX_100_SHEETS {
            @Override
            public SIZE getSize(SIZE size) {
                for (int i = 0; i < SIZE.values().length; i++) {
                    if (SIZE.values()[i].equals(size)) {
                        return SIZE.values().clone()[i];
                    }
                }
                return null;
            }
        }, AGFA_D7_BLR {
            @Override
            public SIZE getSize(SIZE size) {
                for (int i = 0; i < SIZE.values().length; i++) {
                    if (SIZE.values()[i].equals(size)) {
                        return SIZE.values().clone()[i];
                    }
                }
                return null;
            }
        },
        AGFA_D7_ROLLPAC {
            @Override
            public SIZE getSize(SIZE size) {
                for (int i = 0; i < SIZE.values().length; i++) {
                    if (SIZE.values()[i].equals(size)) {
                        return SIZE.values().clone()[i];
                    }
                }
                return null;
            }
        },
        AGFA_D7_ROLLPAC_PB_WITH_LEAD_SCREENS {
            @Override
            public SIZE getSize(SIZE size) {
                for (int i = 0; i < SIZE.values().length; i++) {
                    if (SIZE.values()[i].equals(size)) {
                        return SIZE.values().clone()[i];
                    }
                }
                return null;
            }
        }, KODAK_MX_125_FILM_ROLL_WITH_LEAD_SCREENS {
            @Override
            public SIZE getSize(SIZE size) {
                for (int i = 0; i < SIZE.values().length; i++) {
                    if (SIZE.values()[i].equals(size)) {
                        return SIZE.values().clone()[i];
                    }
                }
                return null;
            }
        },
        KODAK_MX_125_FILM_SHEET_BOX_100_SHEETS {
            @Override
            public SIZE getSize(SIZE size) {
                for (int i = 0; i < SIZE.values().length; i++) {
                    if (SIZE.values()[i].equals(size)) {
                        return SIZE.values().clone()[i];
                    }
                }
                return null;
            }
        },
        KODAK_DR_50_FILM_ROLL_WITH_LEAD_SCREENS {
            @Override
            public SIZE getSize(SIZE size) {
                for (int i = 0; i < SIZE.values().length; i++) {
                    if (SIZE.values()[i].equals(size)) {
                        return SIZE.values().clone()[i];
                    }
                }
                return null;
            }
        },
        KODAK_DR_50_FILM_SHEET_BOX_100_SHEETS {
            @Override
            public SIZE getSize(SIZE size) {
                for (int i = 0; i < SIZE.values().length; i++) {
                    if (SIZE.values()[i].equals(size)) {
                        return SIZE.values().clone()[i];
                    }
                }
                return null;
            }
        },
        KODAK_MX_100_FILM_ROLL_WITH_LEAD_SCREENS {
            @Override
            public SIZE getSize(SIZE size) {
                for (int i = 0; i < SIZE.values().length; i++) {
                    if (SIZE.values()[i].equals(size)) {
                        return SIZE.values().clone()[i];
                    }
                }
                return null;
            }
        },
        KODAK_MX_100_FILM_SHEET_BOX_100_SHEETS {
            @Override
            public SIZE getSize(SIZE size) {
                for (int i = 0; i < SIZE.values().length; i++) {
                    if (SIZE.values()[i].equals(size)) {
                        return SIZE.values().clone()[i];
                    }
                }
                return null;
            }
        },
        KODAK_T_200_FILM_ROLL_WITH_LEAD_SCREENS {
            @Override
            public SIZE getSize(SIZE size) {
                for (int i = 0; i < SIZE.values().length; i++) {
                    if (SIZE.values()[i].equals(size)) {
                        return SIZE.values().clone()[i];
                    }
                }
                return null;
            }
        },
        KODAK_T_200_FILM_SHEET_BOX_100_SHEETS {
            @Override
            public SIZE getSize(SIZE size) {
                for (int i = 0; i < SIZE.values().length; i++) {
                    if (SIZE.values()[i].equals(size)) {
                        return SIZE.values().clone()[i];
                    }
                }
                return null;
            }
        },
        KODAK_AA_400_1_FILM_NIF_BOX_100_SHEETS {
            @Override
            public SIZE getSize(SIZE size) {
                for (int i = 0; i < SIZE.values().length; i++) {
                    if (SIZE.values()[i].equals(size)) {
                        return SIZE.values().clone()[i];
                    }
                }
                return null;
            }
        }, KODAK_AA_400_382_FILM_LEADPACK_ROLL_WITH_LEAD_SCREENS {
            @Override
            public SIZE getSize(SIZE size) {
                for (int i = 0; i < SIZE.values().length; i++) {
                    if (SIZE.values()[i].equals(size)) {
                        return SIZE.values().clone()[i];
                    }
                }
                return null;
            }
        };

        public abstract SIZE getSize(SIZE size);

    }

    public abstract DESCRIPTION getDescription(DESCRIPTION description);

}
