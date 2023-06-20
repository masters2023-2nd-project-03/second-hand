//
//  LocationAddButton+Constant.swift
//  Second-Hand
//
//  Created by Noah on 2023/06/16.
//

import UIKit

extension LocationAddButton {
    enum Constant { }
}

extension LocationAddButton.Constant {
    enum StringLiteral { }
    enum ImageAsset { }
    enum Layout { }
}

extension LocationAddButton.Constant.StringLiteral {
    static let title = "위치 추가"
}

extension LocationAddButton.Constant.ImageAsset {
    static let plusImage = UIImage(systemName: "plus")
}

extension LocationAddButton.Constant.Layout {
    static let imagePadding: CGFloat = 4
    static let height: CGFloat = 52
    static let borderWidth: CGFloat = 1
    static let borderRadius: CGFloat = 8
}
