def epsilon_closure(states, transition_function):
    stack = list(states)
    closure = set(states)

    while stack:
        current = stack.pop()
        if (current, 'ε') in transition_function:
            for nxt in transition_function[(current, 'ε')]:
                if nxt not in closure:
                    closure.add(nxt)
                    stack.append(nxt)
    return closure

def run_enfa(transition_function, start_state, accept_states, input_string):    
    current_states = epsilon_closure({start_state}, transition_function)

    for symbol in input_string:
        next_states = set()
        for state in current_states:
            if (state, symbol) in transition_function:
                next_states |= transition_function[(state, symbol)]
        current_states = epsilon_closure(next_states, transition_function)

    return len(current_states.intersection(accept_states)) > 0

if __name__ == "__main__":
    transition_function = {(0, 'ε'): {1, 2},
                           (1, 'ε'): {3},
                           (2, 'ε'): {5},
                           (3, 'a'): {3, 4},
                           (5, 'b'): {5, 6},
                           (4, 'ε'): {7},
                           (6, 'ε'): {7},}
    start_state = 0
    accept_states = {7}
    test_strings = ["aaa", "bbb", "abba", "bbaa",]
    for s in test_strings:
        result = run_enfa(transition_function, start_state, accept_states, s)
        print(f"Input: {s!r:5} | Accepted? {result}")