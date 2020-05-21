import Foundation

var input = [String]()
var result = [[String]]()

func getAnswer(_ array: [[String]], _ count: Int) {
    guard array.count != count else {
        let sortedInput = input.sorted()
        if result.contains(sortedInput) {
            return
        }

        result.append(sortedInput)
        return
    }

    for i in array[count] where !input.contains(i) {
        input.append(i)
        getAnswer(array, count + 1)
        input.removeLast()
    }
}

func solution(_ user_id:[String], _ banned_id:[String]) -> Int {
    var idAnswer = [[String]](repeating: [], count: banned_id.count)

    for user in user_id {
        for banIndex in banned_id.indices {
            guard user.count == banned_id[banIndex].count else { continue }

            if user.isEqual(with: banned_id[banIndex]) {
                idAnswer[banIndex].append(user)
            }
        }
    }

    getAnswer(idAnswer, 0)

    return result.count
}

extension String {
    func isEqual(with banId: String) -> Bool {
        for i in self.indices {
            if banId[i] != "*", self[i] != banId[i] {
                return false
            }
        }
        return true
    }
}
