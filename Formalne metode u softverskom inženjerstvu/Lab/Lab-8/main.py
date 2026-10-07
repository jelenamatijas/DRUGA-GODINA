import argparse
import sys

from regex_lexer import RegexLexer, RegexLexerError
from regex_parser import RegexParser, RegexParserError
from regex_to_nfa import RegexToNFACompiler
from parse_tree_visualizer import ParseTreeVisualizer
from nfa_visualizer import NFAVisualizer

# TODO: Kleene plus (+) support

# Example usage:
# python main.py ab*a -v -t a aa aaa aba abba 
# python main.py "a(b)*(a|ba*b)" -v -t a aa aaa aba abba baaab abbaaaaaa abbaaaaaab
def main():
    # Parse command line arguments
    arg_parser = argparse.ArgumentParser(description='Compile a regular expression to epsilon-NFA')
    arg_parser.add_argument('regex', type=str, help='regular expression or path to file containing a regex')
    arg_parser.add_argument('-f', '--file', action='store_true', help='read regex from a file instead of command line')
    arg_parser.add_argument('-t', '--test', type=str, nargs='*', help='test strings to run against the NFA')
    arg_parser.add_argument('-v', '--visualize-tree', action='store_true', help='visualize the parse tree')
    args = arg_parser.parse_args()

    # Get the regex from file or command line
    if args.file:
        try:
            with open(args.regex, 'r') as f:
                regex = f.read().strip()
        except IOError as e:
            print(f"Error reading file: {e}")
            return 1
    else:
        regex = args.regex

    # Create lexer, parser, and compiler
    lexer = RegexLexer(regex)
    parser = RegexParser(lexer)
    compiler = RegexToNFACompiler()

    # Parse the regex and compile to NFA
    try:
        parse_tree = parser.build_parse_tree()
        
        # Visualize the parse tree if requested
        if args.visualize_tree:
            print("Regex Parse Tree:")
            visualizer = ParseTreeVisualizer()
            visualizer.visualize(parse_tree)
            print()
        
        # Compile the parse tree to an NFA
        nfa = compiler.compile(parse_tree)
        
        # Visualize the NFA
        nfa_visualizer = NFAVisualizer()
        nfa_visualizer.visualize(nfa)
        
        # Test the NFA if test strings were provided
        if args.test:
            nfa_visualizer.visualize_test(nfa, args.test)
        
    except RegexLexerError as e:
        print(f"Lexer error at position {e.position}: {e.message}")
        return 63
    except RegexParserError as e:
        print(f"Parser error at position {e.position}: {e.message}")
        return 127
    except Exception as e:
        print(f"Error: {e}")
        return 1

    return 0

if __name__ == "__main__":
    err_code = main()
    sys.exit(err_code)