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


import com.codenjoy.dojo.services.Direction;
import com.codenjoy.dojo.services.Dice;
import org.junit.Test;

import static com.codenjoy.dojo.services.Direction.ACT;
import static com.codenjoy.dojo.services.Direction.DOWN;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;

public class SolverTest {

    private Dice dice = mock(Dice.class);
    private final YourSolver solver = new YourSolver(dice);

    private void assertB(String board, Direction direction) {
        assertEquals(direction.toString(), solver.get((Board) new Board().forString(board)));
    }

    @Test
    public void should_when() {
        assertB("☼☼☼☼☼☼☼☼☼☼☼☼☼☼☼" +
                "☼☺        # # ☼" +
                "☼ ☼ ☼ ☼#☼ ☼ ☼ ☼" +
                "☼##           ☼" +
                "☼ ☼ ☼#☼ ☼ ☼ ☼ ☼" +
                "☼   #    # #  ☼" +
                "☼ ☼ ☼ ☼#☼ ☼ ☼ ☼" +
                "☼             ☼" +
                "☼#☼ ☼ ☼#☼ ☼ ☼#☼" +
                "☼  #  #       ☼" +
                "☼ ☼ ☼ ☼ ☼ ☼ ☼#☼" +
                "☼ ##      #   ☼" +
                "☼ ☼ ☼ ☼ ☼ ☼ ☼#☼" +
                "☼ #   #  &    ☼" +
                "☼☼☼☼☼☼☼☼☼☼☼☼☼☼☼",
                ACT);
    }

    private void assertMoveRightWhenRightIsEmpty(String board, Direction direction) {
        assertEquals(direction.toString(), solver.get((Board) new Board().forString(board)));
    }
    @Test
    public void shouldMoveRightWhenRightIsEmpty() {
        assertMoveRightWhenRightIsEmpty("☼☼☼☼☼☼☼☼☼☼☼☼☼☼☼" +
                        "☼☺        # # ☼" +
                        "☼☼☼☼☼☼☼☼☼☼☼☼☼☼☼",
                Direction.RIGHT);
    }

    private void assertMoveLeftWhenLeftIsEmpty(String board, Direction direction) {
        assertEquals(direction.toString(), solver.get((Board) new Board().forString(board)));
    }
    @Test
    public void shouldMoveLeftWhenLeftIsEmpty() {
        assertMoveLeftWhenLeftIsEmpty("☼☼☼☼☼☼☼☼☼☼☼☼☼☼☼" +
                        "☼        # # ☺☼" +
                        "☼☼☼☼☼☼☼☼☼☼☼☼☼☼☼",
                Direction.LEFT);
    }
    private void assertPutBombAndMoveRightWhenLeftIsDestroyableWall(String board, String direction) {
        assertEquals(direction, solver.get((Board) new Board().forString(board)));
    }
    @Test
    public void shouldPutBombAndMoveRightWhenLeftIsDestroyableWall() {
        assertPutBombAndMoveRightWhenLeftIsDestroyableWall("☼☼☼☼☼☼☼☼☼☼☼☼☼☼☼" +
                        "☼        # #☺ ☼" +
                        "☼☼☼☼☼☼☼☼☼☼☼☼☼☼☼",
                Direction.RIGHT.ACT(false));
    }
    private void assertPutBombAndMoveLeftWhenRightIsDestroyableWall(String board, String direction) {
        assertEquals(direction, solver.get((Board) new Board().forString(board)));
    }
    @Test
    public void shouldPutBombAndMoveLeftWhenRightIsDestroyableWall() {
        assertPutBombAndMoveLeftWhenRightIsDestroyableWall("☼☼☼☼☼☼☼☼☼☼☼☼☼☼☼" +
                        "☼        ☺# # ☼" +
                        "☼☼☼☼☼☼☼☼☼☼☼☼☼☼☼",
                Direction.LEFT.ACT(false));
    }
    private void assertPutBombAndMoveLeftWhenRightIsOtherBomber(String board, String direction) {
        assertEquals(direction, solver.get((Board) new Board().forString(board)));
    }
    @Test
    public void shouldPutBombAndMoveLeftWhenRightIsOtherBomber() {
        assertPutBombAndMoveLeftWhenRightIsOtherBomber("☼☼☼☼☼☼☼☼☼☼☼☼☼☼☼" +
                        "☼        ☺♥ # ☼" +
                        "☼☼☼☼☼☼☼☼☼☼☼☼☼☼☼",
                Direction.LEFT.ACT(false));
    }
    private void assertMoveLeftWhenRightIsOtherBombBomber(String board, Direction direction) {
        assertEquals(direction, solver.get((Board) new Board().forString(board)));
    }
    @Test
    public void shouldMoveLeftWhenRightIsOtherBombBomber() {
        assertMoveLeftWhenRightIsOtherBombBomber("☼☼☼☼☼☼☼☼☼☼☼☼☼☼☼" +
                        "☼        ☺♠ # ☼" +
                        "☼☼☼☼☼☼☼☼☼☼☼☼☼☼☼",
                Direction.LEFT);
    }
    private void assertPutBombAndMoveLeftWhenRightIsMeatChopper(String board, String direction) {
        assertEquals(direction, solver.get((Board) new Board().forString(board)));
    }
    @Test
    public void PutBombAndMoveLeftWhenRightIsMeatChopper() {
        assertPutBombAndMoveLeftWhenRightIsMeatChopper("☼☼☼☼☼☼☼☼☼☼☼☼☼☼☼" +
                        "☼        ☺& # ☼" +
                        "☼☼☼☼☼☼☼☼☼☼☼☼☼☼☼",
                Direction.LEFT.ACT(false));
    }
    private void assertPutBombAndMoveRightWhenLeftIsMeatChopper(String board, String direction) {
        assertEquals(direction, solver.get((Board) new Board().forString(board)));
    }
    @Test
    public void PutBombAndMoveRightWhenLeftIsMeatChopper() {
        assertPutBombAndMoveRightWhenLeftIsMeatChopper("☼☼☼☼☼☼☼☼☼☼☼☼☼☼☼" +
                        "☼        &☺ # ☼" +
                        "☼☼☼☼☼☼☼☼☼☼☼☼☼☼☼",
                Direction.RIGHT.ACT(false));
    }
    private void assertPutBombAndMoveDownWhenUpIsMeatChopper(String board, String direction) {
        assertEquals(direction, solver.get((Board) new Board().forString(board)));
    }
    @Test
    public void PutBombAndMoveDownWhenUpIsMeatChopper() {
        assertPutBombAndMoveDownWhenUpIsMeatChopper("☼☼☼☼☼" +
                        "☼ & ☼" +
                        "☼ ☺ ☼" +
                        "☼   ☼" +
                        "☼☼☼☼☼",
                Direction.DOWN.ACT(false));
    }
    private void assertMoveDownWhenUpIsEmpty(String board, String direction) {
        assertEquals(direction, solver.get((Board) new Board().forString(board)));
    }
    @Test
    public void MoveDownWhenUpIsEmpty() {
        assertMoveDownWhenUpIsEmpty("☼☼☼☼☼" +
                        "☼   ☼" +
                        "☼ ☺ ☼" +
                        "☼   ☼" +
                        "☼☼☼☼☼",
                Direction.DOWN.toString());
    }

}
