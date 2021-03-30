# Контрольная работа
# Вариант 56
# ПИ19-3 Данилин А.А.

from functools import lru_cache
import numpy as np

# Задание 1

def t_prob(matrix, step):                                     
    """Вероятность перехода из x в y за step шагов"""
    return np.linalg.matrix_power(matrix, step)

@lru_cache(maxsize=None)
def makyr(step):
    return t_prob(matrix, step) - sum([makyr(m) * t_prob(matrix, step - m) for m in range(1, step)])

@lru_cache(maxsize=None)
def makyr2(step):
    res = t_prob(matrix, step) - sum([makyr2(m) * t_prob(matrix, step - m) for m in range(1, step)])
    result.append(np.diagonal(res))
    return res

@lru_cache(maxsize=None)
def makyr3(step):
    res = t_prob(matrix, step) - sum([makyr3(m) * t_prob(matrix, step - m) for m in range(1, step)])
    result.append(step * np.diagonal(res))
    return res

def mat_power_skip(left, right):   
    rn = range(len(left))
    return np.array([[sum(left[i, m] * right[m, j] if m != j else 0 for m in rn) for j in rn] for i in rn])

def matrix_printer(matrix):
    for row in matrix:
        ryad=''
        for digit in row:
            if(digit==0):
                digit=int(digit)
            ryad+=str(digit)+', '
        print(ryad[:-2:])

trans_mat = np.array([
    [0.35, 0, 0, 0, 0.63, 0.02, 0, 0, 0, 0, 0, 0, 0, 0, 0],
    [0, 0.38, 0, 0.23, 0, 0.39, 0, 0, 0, 0, 0, 0, 0, 0, 0],
    [0, 0, 0.25, 0.17, 0.26, 0.05, 0.27, 0, 0, 0, 0, 0, 0, 0, 0],
    [0.17, 0.03, 0, 0.29, 0.42, 0, 0, 0.09, 0, 0, 0, 0, 0, 0, 0],
    [0.21, 0.15, 0.14, 0.15, 0.14, 0, 0, 0.19, 0.02, 0, 0, 0, 0, 0, 0],
    [0, 0, 0.16, 0, 0.01, 0.05, 0, 0.35, 0.43, 0, 0, 0, 0, 0, 0],
    [0, 0, 0.4, 0, 0, 0.28, 0.05, 0.27, 0, 0, 0, 0, 0, 0, 0],
    [0, 0, 0, 0, 0.04, 0, 0, 0.42, 0.08, 0.21, 0.17, 0, 0.08, 0, 0],
    [0, 0, 0, 0.03, 0, 0.27, 0, 0, 0.04, 0.23, 0, 0.36, 0.07, 0, 0],
    [0, 0, 0, 0, 0, 0, 0, 0, 0.45, 0.06, 0, 0, 0, 0.49, 0],
    [0, 0, 0, 0, 0, 0, 0, 0, 0.26, 0.23, 0.32, 0, 0, 0, 0.19],
    [0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0.09, 0.33, 0.58, 0],
    [0, 0, 0, 0, 0, 0, 0, 0, 0.77, 0, 0, 0, 0.08, 0.15, 0],
    [0, 0, 0, 0, 0, 0, 0, 0, 0, 0.32, 0, 0.49, 0.12, 0.07, 0],
    [0, 0, 0, 0, 0, 0, 0, 0, 0, 0.11, 0.04, 0.38, 0, 0.42, 0.05],
])

print("Матрица переходов:")
matrix_printer(trans_mat)
    
print("\nЗадание 1")

#1
step, state_1, state_2 = 7, 2, 5

print(f"\n1. вероятность того, что за {step} шагов система перейдет из состояния {state_1} в состояние {state_2}:")
print(np.linalg.matrix_power(trans_mat, step)[state_1 - 1, state_2 - 1])

#2

step = 7
A = np.array([0.09, 0.04, 0.17, 0.01, 0.1, 0.14, 0, 0.11, 0.02, 0.11, 0.09, 0.05, 0.04, 0.03, 0])
print(f"\n2. вероятности состояний системы спустя {step} шагов, если в начальный "
      f"момент вероятность состояний были следующими step={A}:")
print((A.dot(np.linalg.matrix_power(trans_mat, step))))

#3

step, state_1, state_2 = 6, 2, 14
prev = np.copy(trans_mat)
for i in range(step - 1):
    prev = mat_power_skip(trans_mat, prev)
print(f"\n3. вероятность первого перехода за {step} шагов из состояния {state_1} в состояние {state_2}:")
print((prev[state_1 - 1, state_2 - 1]))

#4

step, state_1, state_2 = 8, 3, 5
prev, res = np.copy(trans_mat), np.copy(trans_mat)
for i in range(step - 1):
    prev = mat_power_skip(trans_mat, prev)
    res += prev
print(f"\n4. вероятность перехода из состояния {state_1} в состояние {state_2} не позднее чем за {step} шагов:")
print((res[state_1 - 1, state_2 - 1]))

#5

state_1, state_2 = 12, 8
prev, res = np.copy(trans_mat), np.copy(trans_mat)
for i in range(993):
    prev = mat_power_skip(trans_mat, prev)
    res += i * prev
print(f"\n5. среднее количество шагов для перехода из состояния {state_1} в состояние {state_2}:")
print((res[state_1 - 1, state_2 - 1]))

#6

step, state_1 = 7, 9

matrix = np.copy(trans_mat)


print(f"\n6. вероятность первого возвращения в состояние {state_1} за {step} шагов:")
print((np.diagonal(makyr(step))[state_1 - 1]))

#7

step, state_1 = 6, 14

matrix = np.copy(trans_mat)
result = []


makyr2(step)

    
print(f"\n7. вероятность возвращения в состояние {state_1} не позднее чем за {step} шагов")
print((sum(result)[state_1 - 1]))

#8

state_1 = 4

matrix = np.copy(trans_mat)
result = []

makyr3(993)

print(f"\n8. среднее время возвращения в состояние {state_1}")
print((sum(result)[state_1 - 1]))

#9

matrix = np.copy(trans_mat).transpose()
np.fill_diagonal(matrix, np.diagonal(matrix) - 1)
matrix[-1, :] = 1

vect = np.zeros(len(matrix))
vect[-1] = 1
  
print("\n9. установившиеся вероятности:")
print((np.linalg.inv(matrix).dot(vect)))

#Задание 2

print("\nЗадание 2")

def init_matrix(n, m, la, mu):
    size = n + m + 1
    matrix = np.zeros((size, size))
    np.fill_diagonal(matrix[:, 1:], la)
    np.fill_diagonal(matrix[1:, :], [*[i * mu for i in range(1, m)], *[m * mu for j in range(n + 1)]])
    return matrix

def StProbDefiner(matrix):
    t_matrix = np.copy(matrix).transpose()
    np.fill_diagonal(t_matrix, [-sum(t_matrix[:, i]) for i in range(len(matrix))])
    t_matrix[-1, :] = 1

    vect = np.zeros(len(matrix))
    vect[-1] = 1

    return(np.linalg.inv(t_matrix).dot(vect))

la = 29
m = 2
mu = 18
n = 18
matrix = init_matrix(n, m, la, mu)
print(matrix)

#1

print("\na) Составьте граф марковского процесса, запишите систему уравнений Колмогорова и "
      "найдите установившиеся вероятности состояний:")
print(StProbDefiner(matrix))

#2

print("\nb) Найдите вероятность отказа в обслуживании:")
print((StProbDefiner(matrix)[-1]))

#3

print("\nc) Найдите относительную и абсолютную интенсивность обслуживания:")

print((1-StProbDefiner(matrix)[-1], (1-StProbDefiner(matrix)[-1])*la))

#4

print("\nd) Найдите среднюю длину в очереди:")
print((sum((i * StProbDefiner(matrix)[m + i]) for i in range(1, n + 1))))

#5

print("\ne) Найдите среднее время в очереди:")
print((sum(((i + 1) / (m * mu) * StProbDefiner(matrix)[m + i]) for i in range(n))))

#6

print("\nf) Найдите среднее число занятых каналов:")
print(((sum((i * StProbDefiner(matrix)[i]) for i in range(1, m + 1)) + sum((m * StProbDefiner(matrix)[i]) for i in range(m + 1, m + n + 1)))))

#7

print("\ng) Найдите вероятность того, что поступающая заявка не будет ждать в очереди:")
print((sum(StProbDefiner(matrix)[:m])))

#8

print("\nh) Найти среднее время простоя системы массового обслуживания:")

print((1 / np.sum(matrix, axis=1))[0])
