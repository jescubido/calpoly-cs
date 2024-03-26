exception IndexOutOfBounds;
fun element_at([], i) = raise IndexOutOfBounds)
    | element_at(x::xs, 0) = x
    | element_at(x::xs, i) = element_at(xs, i - 1);