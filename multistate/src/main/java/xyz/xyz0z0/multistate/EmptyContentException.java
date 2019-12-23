package xyz.xyz0z0.multistate;

import androidx.annotation.NonNull;

public class EmptyContentException extends RuntimeException {

  EmptyContentException(@NonNull Class<?> clazz) {
    super("There can only one view or view group inside {className}. "
        .replace("{className}", clazz.getSimpleName()));
  }
}



