--
-- Created by IntelliJ IDEA.
-- User: Felicio
-- Date: 07/06/2017
-- Time: 23:37
-- To change this template use File | Settings | File Templates.
--

function HW ()
    print ("Hello World!")
end

print ("Hello World!")

-- defines a factorial fuction
function fact (n)
    if n == 0 then
        return 1
    else
        return n * fact (n - 1)
    end
end

print ("enter a number: ")
a = io.read("*n")   --reads a number
print(fact(a))

function norm (x, y)
    return math.sqrt(x^2 + y^2)
end

function twice (x)
    return 2.0 * x
end

