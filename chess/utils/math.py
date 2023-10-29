from typing import TypeVar, cast

T = TypeVar('T', int, float)

class Math:
    @staticmethod
    def abs(value: T) -> T:
        if value < 0:
            return cast(T, -value)
        return value

    @staticmethod
    def max(*values: T) -> T:
        high_value = values[0]

        for idx in range(1, len(values)):
            if values[idx] > high_value:
                high_value = values[idx]

        return cast(T, high_value)
