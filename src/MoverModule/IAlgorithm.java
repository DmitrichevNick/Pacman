/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MoverModule;

import Enums.MoveType;

/**
 *
 * @author August
 */
public interface IAlgorithm {
    MoveType CalcNextMove(/*Labyrinth labyrinth*/);
}