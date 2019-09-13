package com.codenjoy.dojo.bomberman.client;

/*-
 * #%L
 * Codenjoy - it's a dojo-like platform from developers to developers.
 * %%
 * Copyright (C) 2018 Codenjoy
 * %%
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public
 * License along with this program.  If not, see
 * <http://www.gnu.org/licenses/gpl-3.0.html>.
 * #L%
 */

import com.codenjoy.dojo.bomberman.model.Elements;
import com.codenjoy.dojo.services.Direction;
import com.codenjoy.dojo.client.Solver;
import com.codenjoy.dojo.client.WebSocketRunner;
import com.codenjoy.dojo.services.Dice;
import com.codenjoy.dojo.services.Point;
import com.codenjoy.dojo.services.RandomDice;

/**
 * User: your name
 */
public class YourSolver implements Solver<Board> {
    private String USER_NAME = "counterparty.by@gmail.com";
    private Dice dice;
    private Board board;


    public YourSolver(Dice dice) {
        this.dice = dice;
    }

    /**
     * My logic
     *
     * @param board receives board for this second.
     * @return direction what to do or where to move
     */
    @Override
    public String get(Board board) {

        Point myBomberMan = board.getBomberman();

        this.board = board;
        if (board.isMyBombermanDead()) return "";
        if (board.isBarrierAt(myBomberMan.getX(), myBomberMan.getY())) return "";
        if (board.isAt(myBomberMan.getX() - 1, myBomberMan.getY(), Elements.WALL)) return Direction.RIGHT.toString();
        if (board.isAt(myBomberMan.getX() + 1, myBomberMan.getY(), Elements.WALL)) return Direction.LEFT.toString();
        if (board.isAt(myBomberMan.getX() - 1, myBomberMan.getY(), Elements.DESTROYABLE_WALL)) return Direction.RIGHT.ACT(false);
        if (board.isAt(myBomberMan.getX() + 1, myBomberMan.getY(), Elements.DESTROYABLE_WALL)) return Direction.LEFT.ACT(false);
        if (board.isAt(myBomberMan.getX() + 1, myBomberMan.getY(), Elements.OTHER_BOMBERMAN)) return Direction.LEFT.ACT(false);
        if (board.isAt(myBomberMan.getX() + 1, myBomberMan.getY(), Elements.OTHER_BOMB_BOMBERMAN)) return Direction.LEFT.toString();
        if (board.isAt(myBomberMan.getX() + 1, myBomberMan.getY(), Elements.MEAT_CHOPPER)) return Direction.LEFT.ACT(false);
        if (board.isAt(myBomberMan.getX() - 1, myBomberMan.getY(), Elements.MEAT_CHOPPER)) return Direction.RIGHT.ACT(false);
        if (board.isAt(myBomberMan.getX(), myBomberMan.getY() + 1, Elements.MEAT_CHOPPER)) return Direction.DOWN.ACT(false);
        if (board.isAt(myBomberMan.getX() , myBomberMan.getY()+1, Elements.NONE)) return Direction.DOWN.toString();
        System.out.println(board.toString());
        return Direction.ACT.toString();
    }

    public static void main(String[] args) {
        WebSocketRunner.runClient(
                // paste here board page url from browser after registration
                "http://codenjoy.com:80/codenjoy-contest/board/player/r3t8ms7bnrrbeo6nvjhw?code=3151697663445784467",
                new YourSolver(new RandomDice()),
                new Board());
    }

}
