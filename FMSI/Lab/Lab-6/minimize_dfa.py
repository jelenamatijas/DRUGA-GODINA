from collections import defaultdict

def minimize_dfa(Q, A, T, q0, F):
    # Remove unreachable states.
    R = {q0}
    stack = [q0]
    while stack:
        p = stack.pop()
        for a in A:
            q = T.get((p, a))
            if q not in R:
                R.add(q)
                stack.append(q)
    
    Q  = frozenset(R)
    T  = {(q, a): v for (q, a), v in T.items() if q in Q}
    F  = Q & F

    # Initialize partition P and worklist W.
    P = frozenset({frozenset(S) for S in (F, Q-F) if S})
    W = {min(P, key=len)} # Start with the smaller block for efficiency.)

    # Refinement loop.
    while W:
        w = W.pop()
        for a in A:
            X = frozenset({q for q in Q if T[(q, a)] in w})
            new_P = []
            for B in P:
                intersect = B & X
                diff      = B - X
                if intersect and diff:
                    new_P.extend([intersect, diff])
                    if B in W:
                        W.remove(B)
                        W.extend([intersect, diff])
                    else: 
                        # Optimization: only add the smaller block to W. (We could always add both, but this is more efficient.)
                        W.add(min(intersect, diff, key=len))
                else:
                    new_P.append(B)
            P = new_P

    # Build mapping from each state to its representative.
    rep = {}
    new_Q = set()
    for block in P:
        r = min(block)
        new_Q.add(r)
        for q in block:
            rep[q] = r

    # Build new transition table.
    new_T = {}
    for q in rep.values():
        for a in A:
            new_T[(Q, a)] = rep[T[(q, a)]]

    new_q0 = rep[q0]
    new_F = set(rep.values()) & F

    return new_Q, A, new_T, new_q0, new_F


# Example usage:
if __name__ == "__main__":
    Q = {'A', 'B', 'C', 'D'}
    A = {'0', '1'}
    T = {
        ('A', '0'): 'B', ('A', '1'): 'C',
        ('B', '0'): 'A', ('B', '1'): 'D',
        ('C', '0'): 'D', ('C', '1'): 'A',
        ('D', '0'): 'C', ('D', '1'): 'B'
    }
    q0 = 'A'
    F = {'A', 'D'}

    new_Q, new_A, new_T, new_q0, new_F = minimize_dfa(Q, A, T, q0, F)
    print("Minimized States:", new_Q)
    print("Minimized Transition Table:")
    for key, value in new_T.items():
        print(f"  {key} -> {value}")
    print("Minimized Start State:", new_q0)
    print("Minimized Final States:", new_F)
    print("Number of states in minimized DFA:", len(new_Q))