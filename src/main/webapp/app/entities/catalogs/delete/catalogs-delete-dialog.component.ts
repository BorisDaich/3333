import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

import SharedModule from 'app/shared/shared.module';
import { ICatalogs } from '../catalogs.model';
import { CatalogsService } from '../service/catalogs.service';
import { ITEM_DELETED_EVENT } from 'app/config/navigation.constants';

@Component({
  standalone: true,
  templateUrl: './catalogs-delete-dialog.component.html',
  imports: [SharedModule, FormsModule],
})
export class CatalogsDeleteDialogComponent {
  catalogs?: ICatalogs;

  constructor(protected catalogsService: CatalogsService, protected activeModal: NgbActiveModal) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: string): void {
    this.catalogsService.delete(id).subscribe(() => {
      this.activeModal.close(ITEM_DELETED_EVENT);
    });
  }
}
