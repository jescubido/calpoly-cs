fun is_palindromet(x) =
    let
        fun reverse (i, j) =
            i >= j orelse
            String.sub (x, i) = String.sub(x, j) andalso
            reverse (i + 1) (i - 1)
    in
        reverse 0 (String.size x - 1)
    end;