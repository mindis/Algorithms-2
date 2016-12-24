/*
The basic idea is to repeatedly take the leftmost minimum digit from the string for N - k times, and remove other digits.
Very similarly to Sliding Window Maximum, we can make it with a stack S: for each digit i, we pop all the digits larger than i from S and then push i into S.
Also notice that when there are not enough digits left to compose a (N - k)-digit number, we simply push all digits remained into S.
*/

def removeKdigits(self, num, k):
    N, stack = len(num), []
    for i in xrange(N):
        while 0 < len(stack) > i - k and num[stack[-1]] > num[i]:
            stack.pop()
        stack.append(i)
    stack = "".join([num[i] for i in stack[:N-k]]).lstrip('0') # remove the leading 0's
    return stack if len(stack) > 0 else '0'
