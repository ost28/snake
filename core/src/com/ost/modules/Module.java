package com.ost.modules;

import com.ost.game.GameModel;

public interface Module {

  public static final int EXIT_SUCCESS = 0;
  public static final int EXIT_FAILURE = 1;

  public void load(GameModel gm,Module batch);
  public int run();
  public void unload();
}