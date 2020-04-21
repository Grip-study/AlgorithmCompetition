import Foundation

let exampleBoard = [[0,0,0,0,0],
                    [0,0,1,0,3],
                    [0,2,5,0,1],
                    [4,2,4,4,2],
                    [3,5,1,3,1]]

let moves = [1, 5, 3, 5, 1, 2, 1, 4]

// [1, 5, 3, 5, 1, 2, 1, 4]
// 4 3 1 1 3 2 X 4

typealias Doll = Int

func solution(_ board:[[Int]], _ moves:[Int]) -> Int {
    var board = board
    let boardLength = board.count
    var pickedDollsBasket = [Doll]()
    var disappearedDollsCount = 0

    for move in moves {
        let movedIndex = move - 1
        var pickedDoll: Doll = 0

        for height in 0..<boardLength {
            if board[height][movedIndex] != 0 {
                pickedDoll = board[height].pick(index: movedIndex)
                break
            }
        }

        disappearedDollsCount += pickedDollsBasket.putInsideAndGetDisappearedCount(pickedDoll: pickedDoll)
        print("Move: \(move)  / 뽑은 인형: \(pickedDoll) / 사라진 인형: \(disappearedDollsCount)")
    }

    return disappearedDollsCount
}


extension Array where Element == Doll {
    func element(index: Int) -> Doll? {
        guard self.indices ~= index && self[index] != 0 else { return nil }
        return self[index]
    }

    mutating func pick(index: Int) -> Doll {
        if let pickedDoll = self.element(index: index) {
            self[index] = 0
            return pickedDoll
        } else {
            return 0
        }
    }

    mutating func putInsideAndGetDisappearedCount(pickedDoll: Doll) -> Int {
        guard pickedDoll != 0 else { return 0 }
        self.append(pickedDoll)

        if let priorDoll = self.element(index: self.count - 2), priorDoll == pickedDoll {
            self.removeLast(2)
            return 2
        } else {
            return 0
        }
    }
}

let result = solution(exampleBoard, moves)
print(result)
