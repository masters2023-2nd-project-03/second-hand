//
//  locationAddButton.swift
//  Second-Hand
//
//  Created by PJB on 2023/06/14.
//

import UIKit

class LocationAddButton: UIButton {
    override init(frame: CGRect) {
        super.init(frame: frame)
        self.setupLocationButtonConfiguration()
        self.setupButtonLayoutConstraint()
        self.setupLocationAddButtonLayer()
        self.setupLocationAddButtonContent()
        self.setupLocationAddButtonAppearance()
    }
    
    required init?(coder: NSCoder) {
        super.init(coder: coder)
        self.setupLocationButtonConfiguration()
        self.setupButtonLayoutConstraint()
        self.setupLocationAddButtonLayer()
        self.setupLocationAddButtonContent()
        self.setupLocationAddButtonAppearance()
    }
}

extension LocationAddButton {
    private func setupLocationButtonConfiguration() {
        self.configuration = .plain()
        self.configuration?.imagePadding = Constant.Layout.imagePadding
    }
    
    private func setupLocationAddButtonLayer() {
        self.layer.borderColor = ColorPalette.gray500?.cgColor
        self.layer.borderWidth = Constant.Layout.borderWidth
        self.layer.cornerRadius = Constant.Layout.borderRadius
        self.clipsToBounds = true
    }
    
    private func setupLocationAddButtonContent() {
        self.setTitle(Constant.StringLiteral.title, for: .normal)
        self.setImage(Constant.ImageAsset.plusImage, for: .normal)
    }
    
    private func setupLocationAddButtonAppearance() {
        self.titleLabel?.font = FontStyle.subHead
        self.tintColor = ColorPalette.black
    }
    
    private func setupButtonLayoutConstraint() {
        NSLayoutConstraint.activate(
            [
                self.heightAnchor.constraint(equalToConstant: Constant.Layout.height)
            ]
        )
    }
}
