fun no_dup [] = []
    | no_dup [x] = [x]
    | no_dup(x::y::xs) =
        if x = y then no_dup(y::xs)
        else x::no_dup(y::xs);