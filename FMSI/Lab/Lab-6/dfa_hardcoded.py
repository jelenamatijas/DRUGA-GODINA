def run_hardcoded_example_dfa(input_string):
    state = 'A'
    for symbol in input_string:
        if state == 'A':
            if symbol == '0':
                state = 'B'
            if symbol == '1':
                state = 'C'
        elif state == 'B':
            if symbol == '0':
                state = 'A'
            if symbol == '1':
                state = 'C'
        elif state == 'C':
            if symbol == '0':
                state = 'C'
            if symbol == '1':
                state = 'C'
    return state in {'C'}
    
if __name__ == "__main__":
    input_string = '0101'
    print(f"Does the DFA accept the string '{input_string}'? " +
          f"{run_hardcoded_example_dfa(input_string)}")