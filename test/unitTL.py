from typing import TypeVar


T = TypeVar('T')

class UnitTL:
    @staticmethod
    def assert_list(given: list[T], expected: list[T]) -> bool:
        for given_value in given:
            found = False
            for expected_value in expected:
                if given_value == expected_value:
                    found = True
                    break

            if not found:
                return False
        return True
