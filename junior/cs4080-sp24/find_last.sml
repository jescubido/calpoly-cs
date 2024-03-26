exception EmptyList;
fun find_last (x::[]) = x
    | find_last (x::xs) = find_last xs
    | find_last [] = raise EmptyList;