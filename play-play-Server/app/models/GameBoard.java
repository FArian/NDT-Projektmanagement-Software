package models;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by F.Arian on 08.07.17.
 */
public class GameBoard {
    //The List of LandPieces

    private LandTile[] land;


    // The List of SeaPieces

    private LandTile[] sea;

    //The List of Crosses

    private Cross[] crosses;

    //The List of harbors

    private HarberType[] harbors;

    //Array of harbor Crosses

    private Cross[] harborCrosses;

    //The List of Edge

    private Edge[] edges;


    //Random Instance

    private Random random = new Random();

    /**
     * Gives the index of the landtile, where the robber momentary sits.
     */
    private int robber;

    public GameBoard() {


        land = new LandTile[19];
        crosses = new Cross[54];
        sea = new LandTile[18];
        for (int i = 0; i < 7; i++) {
            crosses[i] = new Cross(new Position(2 + i, 0));
        }
        for (int i = 7; i < 16; i++) {
            crosses[i] = new Cross(new Position(-6 + i, 1));
        }
        for (int i = 16; i < 27; i++) {
            crosses[i] = new Cross(new Position(-16 + i, 2));
        }
        for (int i = 27; i < 38; i++) {
            crosses[i] = new Cross(new Position(-27 + i, 3));
        }
        for (int i = 38; i < 47; i++) {
            crosses[i] = new Cross(new Position(-37 + i, 4));
        }
        for (int i = 47; i < 54; i++) {
            crosses[i] = new Cross(new Position(-45 + i, 5));
        }
        ArrayList<Integer> numbers = new ArrayList<Integer>();
        ArrayList<Integer> numbers2 = new ArrayList<Integer>();
        numbers.add(2);
        numbers.add(3);
        numbers.add(3);
        numbers.add(4);
        numbers.add(4);
        numbers.add(5);
        numbers.add(5);
        numbers2.add(6);
        numbers2.add(6);
        numbers2.add(8);
        numbers2.add(8);
        numbers.add(9);
        numbers.add(9);
        numbers.add(10);
        numbers.add(10);
        numbers.add(11);
        numbers.add(11);
        numbers.add(12);
        ArrayList<Resource> lands = new ArrayList<Resource>();
        lands.add(Resource.Getreide);
        lands.add(Resource.Getreide);
        lands.add(Resource.Getreide);
        lands.add(Resource.Getreide);
        lands.add(Resource.Holz);
        lands.add(Resource.Holz);
        lands.add(Resource.Holz);
        lands.add(Resource.Holz);
        lands.add(Resource.Wolle);
        lands.add(Resource.Wolle);
        lands.add(Resource.Wolle);
        lands.add(Resource.Wolle);
        lands.add(Resource.Erz);
        lands.add(Resource.Erz);
        lands.add(Resource.Erz);
        lands.add(Resource.Lehm);
        lands.add(Resource.Lehm);
        lands.add(Resource.Lehm);

        int k = random.nextInt(19);
        while (k == 1 || k == 8 || k == 11 || k == 14) {
            k = random.nextInt(19);
        }
        // Implements deserts
        land[k] = new LandTile(null, null, true);
        robber = k;
        for (int i = 0; i < 19; i++) {
            if (land[i] == null) {
                if (i == 1 || i == 8 || i == 11 || i == 14) {
                    Integer j = numbers2.remove(random.nextInt(numbers2.size()));
                    LandTile l = new LandTile(j, lands.remove(random.nextInt(lands.size())), false);
                    land[i] = l;
                } else {
                    Integer j = numbers.remove(random.nextInt(numbers.size()));
                    LandTile l = new LandTile(j, lands.remove(random.nextInt(lands.size())), false);
                    land[i] = l;
                }

            }
        }

        // Sets position
        for (int z = 0; z < 1; z++) {
            int index = 0;
            for (int i = -2; i < 1; i++) {
                land[index].setPosition(new Position(i, 2));
                index++;
            }
            for (int i = -2; i < 2; i++) {
                land[index].setPosition(new Position(i, 1));
                index++;
            }
            for (int i = -2; i < 3; i++) {
                land[index].setPosition(new Position(i, 0));
                index++;
            }
            for (int i = -1; i < 3; i++) {
                land[index].setPosition(new Position(i, -1));
                index++;
            }
            for (int i = 0; i < 3; i++) {
                land[index].setPosition(new Position(i, -2));
                index++;
            }
        }

        // generates 18 SeaPieces
        for (int i = 0; i < 18; i++) {
            sea[i] = new LandTile(null, Resource.Meer, false);
        }

        // Sets position
        for (int z = 0; z < 1; z++) {
            int index = 0;
            for (int i = -3; i < 1; i++) {
                sea[index].setPosition(new Position(i, 3));
                index++;
            }
            for (int i = -3; i < 2; i = i + 4) {
                sea[index].setPosition(new Position(i, 2));
                index++;
            }
            for (int i = -3; i < 3; i = i + 5) {
                sea[index].setPosition(new Position(i, 1));
                index++;
            }
            for (int i = -3; i < 4; i = i + 6) {
                sea[index].setPosition(new Position(i, 0));
                index++;
            }
            for (int i = -2; i < 4; i = i + 5) {
                sea[index].setPosition(new Position(i, -1));
                index++;
            }
            for (int i = -1; i < 4; i = i + 4) {
                sea[index].setPosition(new Position(i, -2));
                index++;
            }
            for (int i = 0; i < 4; i++) {
                sea[index].setPosition(new Position(i, -3));
                index++;
            }
        }

        for (int i = 0; i < 4; i++) {
            Cross c[] = new Cross[6];
            c[0] = new Cross(new Position(0, 0));
            c[1] = new Cross(new Position(0, 0));
            c[2] = new Cross(new Position(0, 0));
            c[3] = crosses[Math.abs(-1 + 2 * i)];
            c[4] = crosses[0 + 2 * i];
            c[5] = crosses[1 + 2 * i];
            if (i == 0) {
                c[3] = new Cross(new Position(0, 0));
                sea[i].setCrosses(c);
            } else {

                if (i == 3) {
                    ;
                    c[5] = new Cross(new Position(0, 0));
                    sea[i].setCrosses(c);
                } else {
                    sea[i].setCrosses(c);
                }
            }
        }

        for (int i = -1; i < 4; i++) {
            Cross c[] = new Cross[6];
            c[0] = crosses[Math.abs(0 + 2 * i)];
            c[1] = crosses[Math.abs(1 + 2 * i)];
            c[2] = crosses[Math.abs(2 + 2 * i)];
            c[3] = crosses[Math.abs(8 + 2 * i)];
            c[4] = crosses[Math.abs(9 + 2 * i)];
            c[5] = crosses[Math.abs(10 + 2 * i)];
            if (i == -1) {
                c[0] = new Cross(new Position(0, 0));
                c[1] = new Cross(new Position(0, 0));
                c[3] = new Cross(new Position(0, 0));
                sea[4].setCrosses(c);
            } else {

                if (i == 3) {
                    c[2] = new Cross(new Position(0, 0));
                    c[1] = new Cross(new Position(0, 0));
                    c[5] = new Cross(new Position(0, 0));
                    sea[5].setCrosses(c);
                } else {

                    land[i].setCrosses(c);
                }
            }
        }
        for (int i = 2; i < 8; i++) {
            Cross c[] = new Cross[6];
            c[0] = crosses[1 + 2 * i];
            c[1] = crosses[2 + 2 * i];
            c[2] = crosses[3 + 2 * i];
            c[3] = crosses[11 + 2 * i];
            c[4] = crosses[12 + 2 * i];
            c[5] = crosses[13 + 2 * i];
            if (i == 2) {
                c[0] = new Cross(new Position(0, 0));
                c[1] = new Cross(new Position(0, 0));
                c[3] = new Cross(new Position(0, 0));
                sea[6].setCrosses(c);
            } else {
                if (i == 7) {
                    c[2] = new Cross(new Position(0, 0));
                    c[1] = new Cross(new Position(0, 0));
                    c[5] = new Cross(new Position(0, 0));
                    sea[7].setCrosses(c);
                } else {
                    land[i].setCrosses(c);
                }
            }
        }
        for (int i = 6; i < 13; i++) {
            Cross c[] = new Cross[6];
            c[0] = crosses[2 + 2 * i];
            c[1] = crosses[3 + 2 * i];
            c[2] = crosses[4 + 2 * i];
            c[3] = crosses[13 + 2 * i];
            c[4] = crosses[14 + 2 * i];
            c[5] = crosses[15 + 2 * i];
            if (i == 6) {
                c[0] = new Cross(new Position(0, 0));
                c[1] = new Cross(new Position(0, 0));
                c[3] = new Cross(new Position(0, 0));
                c[4] = new Cross(new Position(0, 0));
                sea[8].setCrosses(c);
            } else {
                if (i == 12) {
                    c[2] = new Cross(new Position(0, 0));
                    c[1] = new Cross(new Position(0, 0));
                    c[4] = new Cross(new Position(0, 0));
                    c[5] = new Cross(new Position(0, 0));
                    sea[9].setCrosses(c);
                } else {
                    land[i].setCrosses(c);
                }
            }
        }
        for (int i = 11; i < 17; i++) {
            Cross c[] = new Cross[6];
            c[0] = crosses[4 + 2 * i];
            c[1] = crosses[5 + 2 * i];
            c[2] = crosses[6 + 2 * i];
            c[3] = crosses[14 + 2 * i];
            c[4] = crosses[15 + 2 * i];
            c[5] = crosses[16 + 2 * i];
            if (i == 11) {
                c[0] = new Cross(new Position(0, 0));
                c[3] = new Cross(new Position(0, 0));
                c[4] = new Cross(new Position(0, 0));
                sea[10].setCrosses(c);
            } else {
                if (i == 16) {
                    c[2] = new Cross(new Position(0, 0));
                    c[4] = new Cross(new Position(0, 0));
                    c[5] = new Cross(new Position(0, 0));
                    sea[11].setCrosses(c);
                } else {
                    land[i].setCrosses(c);
                }
            }
        }
        for (int i = 15; i < 19; i++) {
            Cross c[] = new Cross[6];
            c[0] = crosses[7 + 2 * i];
            c[1] = crosses[8 + 2 * i];
            c[2] = crosses[9 + 2 * i];
            c[3] = crosses[15 + 2 * i];
            c[4] = crosses[16 + 2 * i];
            c[5] = crosses[17 + 2 * i];
            if (i == 15) {
                c[0] = new Cross(new Position(0, 0));
                c[3] = new Cross(new Position(0, 0));
                c[4] = new Cross(new Position(0, 0));
                sea[12].setCrosses(c);

            } else {
                land[i].setCrosses(c);
            }
        }
        for (int i = 0; i < 1; i++) {
            Cross c[] = new Cross[6];
            c[2] = new Cross(new Position(0, 0));
            c[4] = new Cross(new Position(0, 0));
            c[5] = new Cross(new Position(0, 0));
            c[0] = crosses[7 + 2 * 19];
            c[1] = crosses[8 + 2 * 19];
            c[3] = crosses[15 + 2 * 19];
            sea[13].setCrosses(c);
        }

        for (int i = 0; i < 4; i++) {
            Cross c[] = new Cross[6];
            c[0] = crosses[46 + 2 * i];
            c[1] = crosses[47 + 2 * i];
            c[2] = new Cross(new Position(0, 0));
            c[3] = new Cross(new Position(0, 0));
            c[4] = new Cross(new Position(0, 0));
            c[5] = new Cross(new Position(0, 0));
            if (i == 0) {
                c[0] = new Cross(new Position(0, 0));
                c[2] = crosses[48 + 2 * i];
                sea[14].setCrosses(c);
            } else {
                if (i == 3) {
                    sea[17].setCrosses(c);
                } else {
                    c[2] = crosses[48 + 2 * i];
                    sea[14 + i].setCrosses(c);
                }
            }
        }

        harbors = new HarberType[9];

        ArrayList<HarberType> h = new ArrayList<HarberType>();
        h.add(HarberType.Getreide);
        h.add(HarberType.Erz);
        h.add(HarberType.Wolle);
        h.add(HarberType.Holz);
        h.add(HarberType.Lehm);
        h.add(HarberType.GENERIC);
        h.add(HarberType.GENERIC);
        h.add(HarberType.GENERIC);
        h.add(HarberType.GENERIC);
        for (int i = 0; i < 9; i++) {
            harbors[i] = h.remove(random.nextInt(h.size()));
        }

        edges = new Edge[72];
        for (int i = 0; i < 6; i++) {
            edges[i] = new Edge(crosses[i], crosses[i + 1]);
        }
        for (int i = 6; i < 14; i++) {
            edges[i] = new Edge(crosses[i + 1], crosses[i + 2]);
        }
        for (int i = 14; i < 24; i++) {
            edges[i] = new Edge(crosses[i + 2], crosses[i + 3]);
        }
        for (int i = 24; i < 34; i++) {
            edges[i] = new Edge(crosses[i + 3], crosses[i + 4]);
        }
        for (int i = 34; i < 42; i++) {
            edges[i] = new Edge(crosses[i + 4], crosses[i + 5]);
        }
        for (int i = 42; i < 48; i++) {
            edges[i] = new Edge(crosses[i + 5], crosses[i + 6]);
        }
        int index = 0;
        for (int i = 48; i < 52; i++) {
            edges[i] = new Edge(crosses[index], crosses[index + 8]);
            index = index + 2;
        }
        index = 7;
        for (int i = 52; i < 57; i++) {
            edges[i] = new Edge(crosses[index], crosses[index + 10]);
            index = index + 2;
        }
        index = 16;
        for (int i = 57; i < 63; i++) {
            edges[i] = new Edge(crosses[index], crosses[index + 11]);
            index = index + 2;
        }
        index = 28;
        for (int i = 63; i < 68; i++) {
            edges[i] = new Edge(crosses[index], crosses[index + 10]);
            index = index + 2;
        }
        index = 39;
        for (int i = 68; i < 72; i++) {
            edges[i] = new Edge(crosses[index], crosses[index + 8]);
            index = index + 2;
        }

        for (int z = 0; z < 1; z++) {
            int[] help = new int[18];
            help[0] = 0;
            help[1] = 1;
            double r = Math.random();
            if (r < 0.5) {
                help[2] = 3;
                help[3] = 4;
            } else {
                help[2] = 4;
                help[3] = 5;
            }
            if (r < 0.5) {
                help[4] = 6;
                help[5] = 14;
            } else {
                help[4] = 14;
                help[5] = 15;
            }

            help[6] = 26;
            help[7] = 37;
            if (r < 0.5) {
                help[8] = 46;
                help[9] = 45;
            } else {
                help[8] = 45;
                help[9] = 53;
            }
            if (r < 0.5) {
                help[10] = 50;
                help[11] = 51;
            } else {
                help[10] = 51;
                help[11] = 52;
            }
            help[12] = 47;
            help[13] = 48;
            if (r < 0.5) {
                help[14] = 27;
                help[15] = 28;
            } else {
                help[14] = 28;
                help[15] = 38;
            }
            if (r < 0.5) {
                help[16] = 7;
                help[17] = 17;
            } else {
                help[16] = 16;
                help[17] = 17;
            }

            harborCrosses = new Cross[18];
            for (int i = 0; i < 18; i++) {
                harborCrosses[i] = crosses[help[i]];
            }
        }


    }


    /**
     * Creates a Gameboard with the information from the server
     *
     * @param card
     */
    public GameBoard(String[][] card) {
        land = new LandTile[19];
        crosses = new Cross[54];
        sea = new LandTile[18];
        for (int i = 0; i < 7; i++) {
            crosses[i] = new Cross(new Position(2 + i, 0));
        }
        for (int i = 7; i < 16; i++) {
            crosses[i] = new Cross(new Position(-6 + i, 1));
        }
        for (int i = 16; i < 27; i++) {
            crosses[i] = new Cross(new Position(-16 + i, 2));
        }
        for (int i = 27; i < 38; i++) {
            crosses[i] = new Cross(new Position(-27 + i, 3));
        }
        for (int i = 38; i < 47; i++) {
            crosses[i] = new Cross(new Position(-37 + i, 4));
        }
        for (int i = 47; i < 54; i++) {
            crosses[i] = new Cross(new Position(-45 + i, 5));
        }

        for (int i = 0; i < 19; i++) {
            if (card[0][i].equals("Wüste")) {
                land[i] = new LandTile(null, null, true, card[5][i], card[6][i]);
                robber = i;
            }
            if (card[0][i].equals("Ackerland")) {
                land[i] = new LandTile(Integer.parseInt(card[1][i]), Resource.Getreide, false, card[5][i], card[6][i]);
            }
            if (card[0][i].equals("Hügelland")) {
                land[i] = new LandTile(Integer.parseInt(card[1][i]), Resource.Lehm, false, card[5][i], card[6][i]);
            }
            if (card[0][i].equals("Weideland")) {
                land[i] = new LandTile(Integer.parseInt(card[1][i]), Resource.Wolle, false, card[5][i], card[6][i]);
            }
            if (card[0][i].equals("Wald")) {
                land[i] = new LandTile(Integer.parseInt(card[1][i]), Resource.Holz, false, card[5][i], card[6][i]);
            }
            if (card[0][i].equals("Gebirge")) {
                land[i] = new LandTile(Integer.parseInt(card[1][i]), Resource.Erz, false, card[5][i], card[6][i]);
            }
        }
    }


    public void setHarborCrosses(Cross[] harborCrosses) {
        this.harborCrosses = harborCrosses;
    }

    //Setter for land

    public void setLand(LandTile[] land) {
        this.land = land;
    }

    //Setter for sea
    public void setSea(LandTile[] sea) {
        this.sea = sea;
    }

    //Setter for harbors
    public void setHarbor(HarberType[] harbors) {
        this.harbors = harbors;
    }


    //Setter for the crossempty
    public void setCrosses(Cross[] crosses) {
        this.crosses = crosses;
    }

    //Setter for edges
    public void setEdges(Edge[] edges) {
        this.edges = edges;
    }

    public void setRandom(Random random) {
        this.random = random;
    }


    //Setter for robber
    public void setRobber(int tile) {
        land[this.robber].setRobber(false);
        this.robber = tile;
        land[this.robber].setRobber(true);

    }

    public Cross[] getHarborCrosses() {
        return harborCrosses;
    }

    //Getter for land
    public LandTile[] getLand() {
        return land;
    }

    //Getter for sea
    public LandTile[] getSea() {
        return sea;
    }

    //Getter for harbors
    public HarberType[] getHarbor() {
        return harbors;
    }

    //Getter for crossempty
    public Cross[] getCrosses() {
        return crosses;
    }

    public Cross getCross(Position pos) throws NullPointerException {
        for (int i = 0; i < crosses.length; i++) {
            if (crosses[i].getPosition().getX() == pos.getX()
                    &&
                    crosses[i].getPosition().getY() == pos.getY()) {
                return crosses[i];
            }
        }
        return null;
    }

    /**
     * Searches the sea with the defined Position
     *
     * @param pos Position
     * @return Cross
     */
    public LandTile getSea(Position pos) throws NullPointerException {
        for (int i = 0; i < sea.length; i++) {
            if (sea[i].getPosition().getX() == pos.getX()
                    &&
                    sea[i].getPosition().getY() == pos.getY()) {
                return sea[i];
            }
        }
        return null;
    }

    /**
     * Searches the land with the defined Position
     *
     * @param pos Position
     * @return Cross
     */
    public LandTile getLand(Position pos) throws NullPointerException {
        for (int i = 0; i < land.length; i++) {
            if (land[i].getPosition().getX() == pos.getX() && land[i].getPosition().getY() == pos.getY()) {
                return land[i];
            }
        }

        return null;
    }

    public int getLandIndex(Position pos) throws NullPointerException {
        for (int i = 0; i < land.length; i++) {
            if (land[i].getPosition().getX() == pos.getX() && land[i].getPosition().getY() == pos.getY()) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Getter for the Edges
     *
     * @return Edge[]
     */
    public Edge[] getEdges() {
        return edges;
    }

    public Random getRandom() {
        return random;
    }

    /**
     * Getter for the position of the robber
     *
     * @return int
     */
    public int getRobber() {
        return robber;
    }

    /**
     * Translates one cross to three positions, representing the positions of
     * the three land tiles surrounding the cross
     *
     * @param cross
     * @return
     */
    public Position[] giveLands(Cross cross) {
        Position[] lands = new Position[3];
        int trigger = 0;
        LandTile[] allSea = getSea();
        LandTile[] allLand = getLand();
        for (int i = 0; i < allLand.length; i++) {
            if (trigger < 3) {
                for (int j = 0; j < 6; j++) {
                    if (allLand[i].getCrosses()[j].equals(cross)) {
                        lands[trigger] = allLand[i].getPosition();
                        trigger++;
                    }
                }
            }
        }
        for (int i = 0; i < allSea.length; i++) {
            if (trigger < 3) {
                for (int j = 0; j < 6; j++) {
                    if (allSea[i].getCrosses()[j] != null && allSea[i].getCrosses()[j].equals(cross)) {
                        lands[trigger] = allSea[i].getPosition();
                        trigger++;
                    }
                }
            }
        }
        return lands;
    }

    /**
     * Translates two crossempty (equals an edge!) to two positions, representing
     * the positions of the two land tiles surrounding the edge
     *
     * @param cross1
     * @param cross2
     * @return Position[]
     */

    public Position[] giveLands(Cross cross1, Cross cross2) {
        Position[] cross1Lands = giveLands(cross1);
        Position[] cross2Lands = giveLands(cross2);
        Position[] lands = new Position[2];
        int trigger = 0;
        for (int i = 0; i < cross1Lands.length; i++) {
            for (int j = 0; j < cross2Lands.length; j++) {
                if (cross1Lands[i] == cross2Lands[j]) {
                    lands[trigger] = cross1Lands[i];
                    trigger++;
                }
            }
        }
        return lands;
    }

    /**
     * Getter for the harbors
     *
     * @return Position[][]
     */
    public Position[][] harborPos() {
        int harbor1 = 0;
        int harbor2 = 1;
        Position[][] pos = new Position[9][];
        for (int i = 0; i < 9; i++) {
            pos[i] = giveLands(harborCrosses[harbor1], harborCrosses[harbor2]);
            harbor1 = harbor1 + 2;
            harbor2 = harbor2 + 2;
        }
        return pos;
    }

    /**
     * Getter for the harbours
     *
     * @return HarborTypes[]
     */
    public HarberType[] getHarbors() {
        return harbors;
    }

    /**
     * Reorders all the tiles that are sea tiles
     *
     * @return Land[]
     */
    public LandTile[] reorder() {
        int index = 0;
        LandTile[] gamestart = new LandTile[37];
        for (int i = 0; i < 5; i++) {
            gamestart[index] = sea[i];
            index++;
        }
        for (int i = 0; i < 3; i++) {
            gamestart[index] = land[i];
            index++;
        }
        for (int i = 5; i < 7; i++) {
            gamestart[index] = sea[i];
            index++;
        }
        for (int i = 3; i < 7; i++) {
            gamestart[index] = land[i];
            index++;
        }
        for (int i = 7; i < 9; i++) {
            gamestart[index] = sea[i];
            index++;
        }
        for (int i = 7; i < 12; i++) {
            gamestart[index] = land[i];
            index++;
        }
        for (int i = 9; i < 11; i++) {
            gamestart[index] = sea[i];
            index++;
        }
        for (int i = 12; i < 16; i++) {
            gamestart[index] = land[i];
            index++;
        }
        for (int i = 11; i < 13; i++) {
            gamestart[index] = sea[i];
            index++;
        }
        for (int i = 16; i < 19; i++) {
            gamestart[index] = land[i];
            index++;
        }
        for (int i = 13; i < 18; i++) {
            gamestart[index] = sea[i];
            index++;
        }
        return gamestart;
    }

}
