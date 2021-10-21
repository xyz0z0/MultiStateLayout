package xyz.xyz0z0.multistate

import java.lang.RuntimeException

class EmptyContentException internal constructor(clazz: Class<*>) : RuntimeException(
    "There can only one view or view group inside {className}. "
        .replace("{className}", clazz.simpleName)
)