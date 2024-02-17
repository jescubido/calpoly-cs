import random

card_suits = ['Hearts', 'Diamond', 'Club', 'Spades']
card_numbers = ['Ace', '2', '3', '4', '5', '6', '7',
                '8', '9', '10', 'Jack', 'Queen', 'King']
deck = [(card, suit) for suit in card_suits for card in card_numbers]

def card_value(card):
    if card[0] in ['Jack', 'Queen', 'King']:
        return 10
    elif card[0] == 'Ace':
        return 11
    else:
        return int(card[0])
    
random.shuffle(deck)
